package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X graphics expose event. */
public final class GraphicsExpose extends Event {
	public static final int CODE = 13;

	private int drawableID;

	private int x;
	private int y;
	private int width;
	private int height;

	private int minorOpcode;
	private int count;
	private int majorOpcode;

	public GraphicsExpose(Display display, ResponseInputStream in) {

		super(display, in);
		drawableID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		width = in.readInt16();
		height = in.readInt16();
		minorOpcode = in.readInt16();
		count = in.readInt16();
		majorOpcode = in.readInt8();
		in.skip(11);

	}

	public static int getCODE() {
		return CODE;
	}

	public int getDrawableID() {
		return drawableID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getMinorOpcode() {
		return minorOpcode;
	}

	public int getCount() {
		return count;
	}

	public int getMajorOpcode() {
		return majorOpcode;
	}
}
