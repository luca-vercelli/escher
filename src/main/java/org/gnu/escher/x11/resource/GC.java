package org.gnu.escher.x11.resource;

import org.gnu.escher.x11.Color;
import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.GCValues;
import org.gnu.escher.x11.RequestObject;
import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.enums.ArcMode;
import org.gnu.escher.x11.enums.CapStyle;
import org.gnu.escher.x11.enums.FillRule;
import org.gnu.escher.x11.enums.FillStyle;
import org.gnu.escher.x11.enums.FunctionValues;
import org.gnu.escher.x11.enums.JoinStyle;
import org.gnu.escher.x11.enums.LineStyle;
import org.gnu.escher.x11.enums.RectangleOrder;
import org.gnu.escher.x11.enums.SubWindowMode;
import org.gnu.escher.x11.enums.X11CoreRequest;
import org.gnu.escher.x11.types.Rectangle;

/**
 * X graphics context. This is used to change settings for drawing.
 */
public class GC extends Fontable {

	/**
	 * Aggregates ChangeGC requests.
	 */
	private class ChangeGCRequestObject implements RequestObject {

		/**
		 * The values that are changes.
		 */
		GCValues values;

		ChangeGCRequestObject() {
			values = new GCValues();
		}

		void clear() {
			values.clear();
		}

		/**
		 * Creates a new ChangeGCRequestObject.
		 *
		 * @param v the values to be changed
		 */
		ChangeGCRequestObject(GCValues v) {
			values = v;
		}

		/**
		 * Writes this object to the connection stream.
		 *
		 */
		public void write(RequestOutputStream s) {

			s.setIndex(2);
			s.writeInt16(3 + values.count());
			s.writeInt32(GC.this.id);
			s.writeInt32(values.getBitmask());
			values.write(s);
		}

	}

	private ChangeGCRequestObject changeGCRequest = new ChangeGCRequestObject();

	public GC(Display display) {
		super(display);
	}

	public GC(GC src, int mask) {
		super(src.display);
		src.copy(this, mask);
	}

	public GC(GC src) {
		this(src, GCValues.ALL);
	}

	public GC(Display display, GCValues values) {
		this(display.getRootWindow(), values);
	}

	/**
	 * @see #create(Drawable, GC.GCValues)
	 */
	public GC(Drawable drawable, GCValues values) {
		this(drawable.getDisplay());
		create(drawable, values);
	}

	/**
	 * @see #GC(Drawable, GC.GCValues)
	 */
	public GC(Drawable drawable) {
		this(drawable, GCValues.EMPTY);
	}

	// opcode 55 - create gc
	/**
	 * Creates a GC for the specified drawable with the specified values.
	 *
	 * @see <a href="XCreateGC.html">XCreateGC</a>
	 */
	public void create(Drawable drawable, GCValues values) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.CreateGC.getOpcode(), 0, 4 + values.count());
			o.writeInt32(id);
			o.writeInt32(drawable.getId());
			o.writeInt32(values.getBitmask());
			values.write(o);
			o.send();
		}
	}

	// opcode 56 - change gc
	/**
	 * Changes the current settings for this GC. This request will be aggregated.
	 *
	 * @param values the values to change
	 *
	 * @see <a href="XChangeGC.html">XChangeGC</a>
	 */
	public void change(GCValues values) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.ChangeGC.getOpcode(), 0, 0);
			ChangeGCRequestObject cr = new ChangeGCRequestObject(values);
			cr.write(o);
			o.send();
		}
	}

	// opcode 57 - copy gc
	/**
	 * Copies the state from this GC into the specified destination GC. The mask is
	 * used to include/exclude specific state.
	 *
	 * @param dest the destination GC
	 * @param mask the state mask
	 *
	 * @see <a href="XCopyGC.html">XCopyGC</a>
	 */
	public void copy(GC dest, int mask) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.CopyGC.getOpcode(), 0, 4);
			o.writeInt32(id); // Src-ID.
			o.writeInt32(dest.id); // Dst-ID.
			o.writeInt32(mask);
			o.send();
		}

	}

	// opcode 58 - set dashes
	/**
	 * Sets the dashes used for drawing lines.
	 *
	 * @param dashOffset the dash offset
	 * @param dashes     the dashes spec
	 *
	 * @see <a href="XSetDashes.html">XSetDashes</a>
	 */
	public void setDashes(int dashOffset, byte[] dashes) {

		int n = dashes.length;
		int p = 4 - (n % 4);

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.SetDashes.getOpcode(), 0, 3 + (n + p) / 4);

			o.writeInt32(id); // The GC id.
			o.writeInt16(dashOffset); // The dash offset.
			o.writeInt16(n); // The number of dashes.
			o.writeBytes(dashes); // The actual dashes.

			for (int i = 0; i < p; i++)
				o.writeInt8(0); // Pad.

			o.send();
		}
	}

	// opcode 59 - set clip rectangles
	/**
	 * @see <a href="XSetClipRectangles.html">XSetClipRectangles</a>
	 */
	public void setClipRectangles(int clip_x_origin, int clip_y_origin, Rectangle[] rectangles,
			RectangleOrder ordering) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.SetClipRectangles.getOpcode(), ordering.getCode(), 3 + 2 * rectangles.length);
			o.writeInt32(id);
			o.writeInt16(clip_x_origin);
			o.writeInt16(clip_y_origin);

			for (Rectangle rec : rectangles) {
				o.writeInt16(rec.getX());
				o.writeInt16(rec.getY());
				o.writeInt16(rec.getWidth());
				o.writeInt16(rec.getHeight());
			}
			o.send();
		}
	}

	// opcode 60 - free gc
	/**
	 * @see <a href="XFreeGC.html">XFreeGC</a>
	 */
	public void free() {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.FreeGC.getOpcode(), 0, 2);
			o.writeInt32(id);
			o.send();
		}
	}

	/**
	 * @see #GC(Drawable)
	 */
	public static GC build(Display display) {
		return new GC(display.getRootWindow());
	}

	/**
	 * @see #create(GC.GCValues)
	 */
	public void create() {
		create(GCValues.EMPTY);
	}

	/**
	 * @see #create(Drawable, GC.GCValues)
	 */
	public void create(GCValues values) {
		create(display.getRootWindow(), values);
	}

	/**
	 * @see #copy(int)
	 */
	public GC copy() {
		return copy(GCValues.ALL);
	}

	/**
	 * @see #copy(GC, int)
	 */
	public GC copy(int mask) {
		GC gc = build(display);
		copy(gc, mask);
		return gc;
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setArcMode(ArcMode)
	 */
	public void setArcMode(ArcMode am) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setArcMode(am);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setArcMode(am);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #setBackground(int)
	 */
	public void setBackground(Color c) {
		setBackground(c.getPixel());
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setBackground(int)
	 */
	public void setBackground(int pixel) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setBackground(pixel);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setBackground(pixel);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setCapStyle(CapStyle)
	 */
	public void setCapStyle(CapStyle cs) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setCapStyle(cs);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setCapStyle(cs);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setClipMask(Pixmap)
	 */
	public void setClipMask(Pixmap pixmap) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setClipMask(pixmap);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setClipMask(pixmap);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setClipXOrigin(int)
	 */
	public void setClipXOrigin(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setClipXOrigin(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setClipXOrigin(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setClipYOrigin(int)
	 */
	public void setClipYOrigin(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setClipYOrigin(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setClipYOrigin(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setDashes(int)
	 */
	public void setDashes(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setDashes(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setDashes(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setDashOffset(int)
	 */
	public void setDashOffset(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setDashOffset(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setDashOffset(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setFillRule(FillRule fr) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setFillRule(fr);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setFillRule(fr);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setFillStyle(FillStyle fs) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setFillStyle(fs);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setFillStyle(fs);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#set_font(Font)
	 */
	public void setFont(Font font) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.set_font(font);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.set_font(font);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #setForeground(int)
	 */
	public void setForeground(Color c) {
		setForeground(c.getPixel());
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setForeground(int)
	 */
	public void setForeground(int pixel) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setForeground(pixel);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setForeground(pixel);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setFunction(FunctionValues fv) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setFunction(fv);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setFunction(fv);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setGraphicsExposures(boolean)
	 */
	public void setGraphicsExposures(boolean b) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setGraphicsExposures(b);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setGraphicsExposures(b);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setJoinSty(JoinStyle js) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setJoinStyle(js);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setJoinStyle(js);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setLineStyle(LineStyle ls) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setLineStyle(ls);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setLineStyle(ls);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setLineWidth(int)
	 */
	public void setLineWidth(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setLineWidth(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setLineWidth(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setPlaneMask(int)
	 */
	public void setPlaneMask(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setPlaneMask(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setPlaneMask(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setStipple(Pixmap)
	 */
	public void setStipple(Pixmap pixmap) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setStipple(pixmap);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setStipple(pixmap);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 */
	public void setSubwindowMode(SubWindowMode swm) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setSubwindowMode(swm);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setSubwindowMode(swm);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setTile(Pixmap)
	 */
	public void setTile(Pixmap pixmap) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setTile(pixmap);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setTile(pixmap);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setTileStippleXOrigin(int)
	 */
	public void setTileStippleXOrigin(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setTileStippleXOrigin(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setTileStippleXOrigin(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}

	/**
	 * @see #change(GC.GCValues)
	 * @see GCValues#setTileStippleYOrigin(int)
	 */
	public void setTileStippleYOrigin(int i) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			if (o.opcode() == 56 && o.getRequestObject() instanceof ChangeGCRequestObject) {
				// Aggregate request.
				ChangeGCRequestObject r = (ChangeGCRequestObject) o.getRequestObject();
				r.values.setTileStippleXOrigin(i);
			} else {
				o.beginRequest(56, 0, 0);
				changeGCRequest.clear();
				changeGCRequest.values.setTileStippleXOrigin(i);
				o.setRequestObject(changeGCRequest);
			}
		}
	}
}
