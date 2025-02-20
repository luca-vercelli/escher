package org.gnu.escher.x11.resource;

import org.gnu.escher.x11.Color;
import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.RGB;
import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.ResponseInputStream;

/** X colormap. */
public class Colormap extends Resource {
	/**
	 * Predefined colormap.
	 *
	 * @see Window#NONE
	 */
	public static final Colormap COPY_FROM_PARENT = new Colormap(0);

	/** Predefined. */
	public Colormap(int id) {
		super(id);
	}

	/** Create. */
	public Colormap(Display display) {
		super(display);
	}

	/** Intern. Create empty resource with given id */
	public Colormap(Display display, int id) {
		super(display, id);
	}

	public static final int NONE = 0;
	public static final int ALL = 1;

	// opcode 78 - create colormap
	/**
	 * Creates a colormap of the specified visual type for the screen on which the
	 * window resides. The visual type must be supported by the screen (or a Match
	 * error results). The initial values of the colormap entries are undefined for
	 * classes GrayScale, PseudoColor and DirectColor. For StaticGray, StaticColor
	 * and TrueColor <code>alloc</code> must be specified as <code>NONE</code> (or a
	 * Match error results). For the other classes, if alloc is <code>NONE</code>
	 * the colormap initially has no allocated entries, and clients can allocate
	 * entries. If alloc is <code>ALL</code>, then the entire colormap is allocated
	 * writable. The initial values of all allocated entries are undeï¬�ned. For
	 * GrayScale and PseudoColor, the effect is as if an AllocColorCells request
	 * returned all pixel values from zero to N âˆ’ 1, where N is the
	 * colormap-entries value in the speciï¬�ed visual. For DirectColor, the effect
	 * is as if an AllocColorPlanes request returned a pixel value of zero and
	 * red-mask, green-mask, and blue-mask values containing the same bits as the
	 * corresponding masks in the speciï¬�ed visual. However, in all cases, none of
	 * these entries can be freed with FreeColors.
	 *
	 * @param window
	 *            the window for which the colormap is allocated
	 * @param visualId
	 *            the visual type
	 * @param alloc
	 *            one of {@link #NONE}, {@link #ALL}, see above for explanation
	 *
	 * @see <a href="XCreateColormap.html">XCreateColormap</a>
	 */
	public Colormap(Window window, int visualId, int alloc) {
		super(window.display);

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(78, alloc, 4);
			o.writeInt32(id);
			o.writeInt32(window.id);
			o.writeInt32(visualId);
			o.send();
		}
	}

	// opcode 79 - free colormap
	/**
	 * @see <a href="XFreeColormap.html">XFreeColormap</a>
	 */
	public void free() {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(78, 0, 2);
			o.writeInt32(id);
			o.send();
		}
	}

	// opcode 80 - copy colormap and free
	public Colormap copyAndFree(int newID) {

		Colormap newMap = new Colormap(display, newID);
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(80, 0, 3);
			o.writeInt32(newID);
			o.writeInt32(id);
			o.send();
		}
		return newMap;
	}

	// opcode 81 - install colormap
	/**
	 * @see <a href="XInstallColormap.html">XInstallColormap</a>
	 */
	public void install() {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(81, 0, 3);
			o.writeInt32(id);
			o.send();
		}
	}

	// opcode 82 - uninstall colormap
	/**
	 * @see <a href="XUninstallColormap.html">XUninstallColormap</a>
	 */
	public void uninstall() {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(82, 0, 3);
			o.writeInt32(id);
			o.send();
		}
	}

	/**
	 * Get Resource with given id if exists, or create it
	 */
	public static Object intern(Display display, int id) {
		Object value = display.getResources().get(new Integer(id));
		if (value != null)
			return value;
		return new Colormap(display, id);
	}

	// opcode 84 - alloc color
	/**
	 * Allocates a read-only colormap entry corresponding to the closest RGB values
	 * provided by the hardware. It also returns the pixel and RGB values actually
	 * used. Multiple clients requesting the same effective RGB values can be
	 * assigned the same read-only entry, allowing entries to be shared.
	 *
	 * @param red
	 *            the red component
	 * @param green
	 *            the green component
	 * @param blue
	 *            the blue component
	 *
	 * @return the closest pixel and RGB value of the requested entry
	 *
	 * @see <a href="XAllocColor.html">XAllocColor</a>
	 */
	public Color allocColor(int red, int green, int blue) {
		RequestOutputStream o = display.getResponseOutputStream();
		Color c;
		synchronized (o) {
			o.beginRequest(84, 0, 4);
			o.writeInt32(id);
			o.writeInt16(red);
			o.writeInt16(green);
			o.writeInt16(blue);
			o.skip(2);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int r = i.readInt16();
				int g = i.readInt16();
				int b = i.readInt16();
				i.skip(2);
				int p = i.readInt32();
				i.skip(12);
				c = new Color(p);
				c.setExact(new RGB(r, g, b));
			}
		}
		return c;
	}

	// opcode 85 - alloc named color
	/**
	 * Looks up the named color with respect to the screen associated with the
	 * colormap. Then it does an AllocColor on the colormap. The name should use ISO
	 * Latin-1 encoding, and uppercase and lowercase do not matter. The exact RGB
	 * values specify the true values for the color, and the visual values specify
	 * the values actually used in the colormap.
	 * 
	 * @param name
	 *            the name of the color
	 *
	 * @return the allocated color
	 *
	 * @see <a href="XAllocNamedColor.html">XAllocNamedColor</a>
	 */
	public Color allocNamedColor(String name) {

		RequestOutputStream o = display.getResponseOutputStream();
		int n = name.length();
		int p = RequestOutputStream.pad(n);

		Color c;
		synchronized (o) {
			o.beginRequest(85, 0, 3 + (n + p) / 4);
			o.writeInt32(id);
			o.writeInt16(n);
			o.skip(2);
			o.writeString8(name);
			o.skip(p);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int pixel = i.readInt32();
				int er = i.readInt16();
				int eg = i.readInt16();
				int eb = i.readInt16();
				int vr = i.readInt16();
				int vg = i.readInt16();
				int vb = i.readInt16();
				i.skip(8);
				c = new Color(pixel);
				c.setExact(new RGB(er, eg, eb));
				c.setVisual(new RGB(vr, vg, vb));
			}
		}
		return c;
	}

	/**
	 * Reply of {@link #allocColorCells(boolean, int, int)}.
	 */
	// FIXME: Improve API somehow here.
	public static class ColorCellsReply {
		public int[] pixels;
		public int[] masks;

		ColorCellsReply(int[] p, int[] m) {
			pixels = p;
			masks = m;
		}
	}

	// opcode 86 - alloc color cells
	/**
	 * @see <a href="XAllocColorCells.html">XAllocColorCells</a>
	 */
	public ColorCellsReply allocColorCells(boolean contiguous, int color_count, int plane_count) {

		RequestOutputStream o = display.getResponseOutputStream();
		ColorCellsReply r;
		synchronized (o) {
			o.beginRequest(86, contiguous ? 1 : 0, 3);
			o.writeInt32(id);
			o.writeInt16(color_count);
			o.writeInt16(plane_count);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int n = i.readInt16();
				int m = i.readInt16();
				i.skip(20);
				int[] pixels = new int[n];
				for (int j = 0; j < n; j++)
					pixels[j] = i.readInt32();
				int[] masks = new int[m];
				for (int j = 0; j < m; j++)
					masks[j] = i.readInt32();
				r = new ColorCellsReply(pixels, masks);
			}
		}
		return r;
	}

	/**
	 * Reply for {@link Colormap#allocPlanes(boolean, int, int, int, int)}.
	 */
	public class ColorPlaneReply {
		public int red_mask;
		public int green_mask;
		public int blue_mask;
		public int[] pixels;

		ColorPlaneReply(int rm, int gm, int bm, int[] px) {
			red_mask = rm;
			green_mask = rm;
			blue_mask = bm;
			pixels = px;
		}
	}

	// opcode 87 - alloc color planes
	/**
	 * @see <a href="XAllocColorPlanes.html">XAllocColorPlanes</a>
	 */
	public ColorPlaneReply allocPlanes(boolean contiguous, int color_count, int red_count, int green_count,
			int blue_count) {

		RequestOutputStream o = display.getResponseOutputStream();
		ColorPlaneReply r;
		synchronized (o) {
			o.beginRequest(87, contiguous ? 1 : 0, 4);
			o.writeInt32(id);
			o.writeInt16(color_count);
			o.writeInt16(red_count);
			o.writeInt16(green_count);
			o.writeInt16(blue_count);

			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int n = i.readInt16();
				i.skip(2);
				int rm = i.readInt32();
				int gm = i.readInt32();
				int bm = i.readInt32();
				i.skip(8);
				int[] px = new int[n];
				for (int j = 0; j < n; j++)
					px[j] = i.readInt32();
				r = new ColorPlaneReply(rm, gm, bm, px);
			}
		}
		return r;
	}

	// opcode 88 - free colors
	/**
	 * @see <a href="XFreeColors.html">XFreeColors</a>
	 */
	public void freeColors(int[] pixels, int planeMask) {

		int n = pixels.length;
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(88, 0, 3 + n);
			o.writeInt32(id);
			o.writeInt32(planeMask);
			for (int i = 0; i < pixels.length; i++)
				o.writeInt32(pixels[i]);
			o.send();
		}
	}

	public static class ColorItem {

		public int pixel;
		public int red;
		public int green;
		public int blue;
		public boolean do_red;
		public boolean do_green;
		public boolean do_blue;

		public ColorItem(int pixel, int red, int green, int blue, boolean do_red, boolean do_green, boolean do_blue) {
			this.pixel = pixel;
			this.red = red;
			this.green = green;
			this.blue = blue;
			this.do_red = do_red;
			this.do_green = do_green;
			this.do_blue = do_blue;
		}

		void write(RequestOutputStream o) {
			o.writeInt32(pixel);
			o.writeInt16(red);
			o.writeInt16(green);
			o.writeInt16(blue);
			int do_colors = (do_red ? 0x01 : 0) | (do_green ? 0x02 : 0) | (do_blue ? 0x04 : 0);
			o.writeInt8(do_colors);
			o.skip(1);
		}
	}

	// opcode 89 - store colors
	/**
	 * @see <a href="XStoreColors.html">XStoreColors</a>
	 */
	public void storeColors(ColorItem[] items) {

		int n = items.length;
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(89, 0, 3 + 2 * n);
			o.writeInt32(id);
			for (int i = 0; i < n; i++)
				items[i].write(o);
			o.send();
		}
	}

	// opcode 90 - store named color
	/**
	 * @see <a href="XStoreNamedColor.html">XStoreNamedColor</a>
	 */
	public void storeNamedColor(int pixel, String name, boolean do_reds, boolean do_greens, boolean do_blues) {

		int do_color = 0;
		if (do_reds)
			do_color |= 0x01;
		if (do_greens)
			do_color |= 0x02;
		if (do_blues)
			do_color |= 0x04;

		int n = name.length();
		int p = RequestOutputStream.pad(n);

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(90, do_color, 4 + (n + p) / 4);
			o.writeInt32(id);
			o.writeInt32(pixel);
			o.writeInt16(n);
			o.skip(2);
			o.writeString8(name);
			o.skip(p);
			o.send();
		}
	}

	// opcode 91 - query colors
	/**
	 * Returns the hardware specific color values stored in this colormap for the
	 * specified pixels. The values returned for an unallocated entry are undefined.
	 *
	 * @param pixels
	 *            the pixels for which to return the color values
	 *
	 * @return the hardware specific color values of this colormap
	 *
	 * @see <a href="XQueryColors.html">XQueryColors</a>
	 */
	public RGB[] colors(int[] pixels) {

		RequestOutputStream o = display.getResponseOutputStream();
		int n = pixels.length;
		RGB[] rgbs;
		synchronized (o) {
			o.beginRequest(91, 0, 2 + n);
			o.writeInt32(id);
			for (int j = 0; j < n; j++)
				o.writeInt32(pixels[j]);

			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int len = i.readInt16();
				rgbs = new RGB[len];
				i.skip(22);
				for (int j = 0; j < len; j++) {
					int r = i.readInt16();
					int g = i.readInt16();
					int b = i.readInt16();
					i.skip(2);
					rgbs[j] = new RGB(r, g, b);
				}
			}
		}
		return rgbs;
	}

	// opcode 92 - lookup color
	/**
	 * Looks up the name of a color with respect to the screen associated with this
	 * colormap, and returns both the exact color values and the closest values
	 * provided by the hardware with respect to the visual type of this colormap.
	 * The name should use the ISO Latin-1 encoding, and uppercase and lowercase do
	 * not matter.
	 *
	 * @param name
	 *            the color name to lookup
	 *
	 * @return the color
	 *
	 * @see <a href="XLookupColor.html">XLookupColor</a>
	 */
	public Color lookupColor(String name) {

		int n = name.length();
		int p = RequestOutputStream.pad(n);
		RequestOutputStream o = display.getResponseOutputStream();
		Color c;
		synchronized (o) {
			o.beginRequest(92, 0, 3 + (n + p) / 4);
			o.writeInt32(id);
			o.writeInt16(n);
			o.skip(2);
			o.writeString8(name);
			o.skip(p);

			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				int er = i.readInt16();
				int eg = i.readInt16();
				int eb = i.readInt16();
				int vr = i.readInt16();
				int vg = i.readInt16();
				int vb = i.readInt16();
				i.skip(12);
				c = new Color(0);
				c.setExact(new RGB(er, eg, eb));
				c.setVisual(new RGB(vr, vg, vb));
			}
		}
		return c;
	}

	/**
	 * @see #allocColor(int, int, int)
	 */
	public Color allocColor(RGB rgb) {
		return allocColor(rgb.getRed(), rgb.getGreen(), rgb.getBlue());
	}

	/**
	 * @see #allocColor(RGB)
	 */
	public Color allocColor8(int red8, int green8, int blue8) {
		return allocColor(RGB.rgb8(red8, green8, blue8));
	}

	/**
	 * @see #allocColor(int, int, int)
	 */
	public Color allocRandomColor(java.util.Random random) {
		return allocColor(random.nextInt() & 0xffff, random.nextInt() & 0xffff, random.nextInt() & 0xffff);
	}

	/**
	 * @see #allocColor(int, int, int)
	 */
	public Color allocRandomRainbowColor(java.util.Random random) {
		int hue = random.nextInt(360);
		return allocColor(RGB.hsv(hue, 1, 1));
	}
}
