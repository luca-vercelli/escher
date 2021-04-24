package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X expose event. */
public final class Expose extends Event {

	private int windowID;

	private int x;
	private int y;
	private int width;
	private int height;

	private int count;

	public Expose(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		width = in.readInt16();
		height = in.readInt16();
		count = in.readInt16();
		in.skip(14); // Unused.
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

	public int getCount() {
		return count;
	}

	public int getWindowID() {
		return windowID;
	}

}
