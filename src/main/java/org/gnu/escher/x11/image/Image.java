
package org.gnu.escher.x11.image;

import org.gnu.escher.x11.*;
import org.gnu.escher.x11.enums.Format;

public abstract class Image {

	/*
	 * public static enum ByteOrder { LSB_FIRST(0), MSB_FIRST(1);
	 * 
	 * private int id; ByteOrder(int id) { this.id = id; }
	 * 
	 * public int getId() { return this.id; } }
	 */
	// TODO: change this with the above enum
	public static final int LSB_FIRST = 0;
	public static final int MSB_FIRST = 1;

	public static final int LEAST_SIGNIFICANT = 0;

	public static final int MOST_SIGNIFICANT = 1;

	byte[] data;

	int width;

	int height;

	protected int leftPad;

	Format format;

	protected int lineByteCount;

	protected Pixmap.Format pixmapFormat;

	Image(Format f, Pixmap.Format pf) {

		init(0, 0, f, pf);
	}

	/**
	 * Constructs an Image object and creates a new backing array.
	 * 
	 * @param width        the width in pixels
	 * @param height       the height in pixels
	 * @param format       the image format
	 * @param pixmapFormat the pixmap format
	 */
	Image(int width, int height, Format format, Pixmap.Format pixmapFormat) {

		init(width, height, format, pixmapFormat);

		data = new byte[lineByteCount * height];
	}

	/**
	 * Constructs a new Image object with an existing data array.
	 * 
	 * @param width        the width in pixels
	 * @param height       the height in pixels
	 * @param format       the image format
	 * @param pixmapFormat the pixmap format
	 * @param data         the underlying data array
	 */
	Image(int width, int height, Format format, Pixmap.Format pixmapFormat, byte[] data) {

		init(width, height, format, pixmapFormat);
		this.data = data;
	}

	/**
	 * Initializes the internal state of the Image object.
	 * 
	 * @param w  the width
	 * @param h  the height
	 * @param f  the image format
	 * @param pf the pixmap format
	 */
	private void init(int w, int h, Format f, Pixmap.Format pf) {

		width = w;
		height = h;
		format = f;
		pixmapFormat = pf;

		init();
	}

	/**
	 * Calculates the internal state.
	 */
	void init() {

		int lineBitCount = width * pixmapFormat.getBitsPerPixel();

//        System.err.println("getBitsPerPixel: " + pixmapFormat.getBitsPerPixel());
//        System.err.println("lineBitCount: " + lineBitCount);

		int scanlinePad = pixmapFormat.getScanlinePad();

		// do we have to pad?
		int rem = lineBitCount % scanlinePad;

//        System.err.println("scanlinePad: " + scanlinePad);
//        System.err.println("rem: " + rem);

		// and if so, how much?
		int linePadCount = (lineBitCount / scanlinePad) + (rem == 0 ? 0 : 1);
		lineByteCount = (linePadCount * scanlinePad) / 8;

//        System.err.println("lineByteCount: " + lineByteCount);

		leftPad = format == Format.ZPIXMAP ? 0 : lineByteCount * 8 - linePadCount;
	}

	/**
	 * Returns the width of the image in pixels.
	 * 
	 * @return the width of the image in pixels
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the image in pixels.
	 * 
	 * @return the height of the image in pixels
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the image format.
	 * 
	 * @return the image format
	 */
	public Format getFormat() {
		return format;
	}

	public byte[] getData() {
		return data;
	}

	public int getLeftPad() {

		return leftPad;
	}

	public Pixmap.Format getPixmapFormat() {
		return pixmapFormat;
	}

	public int getLineByteCount() {

		return lineByteCount;
	}
}
