package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X create notify event. */
public final class CreateNotify extends Event {

	private int parentID;
	private int windowID;
	private int x;
	private int y;
	private int width;
	private int height;
	private int borderWidth;
	private boolean overrideRedirect;

	public CreateNotify(Display display, ResponseInputStream in) {
		super(display, in);
		parentID = in.readInt32();
		windowID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		width = in.readInt16();
		height = in.readInt16();
		borderWidth = in.readInt16();
		overrideRedirect = in.readBool();
		in.skip(9);
	}

	public int getParentID() {
		return parentID;
	}

	public int getWindowID() {
		return windowID;
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

	public int getBorderWidth() {
		return borderWidth;
	}

	public boolean isOverrideRedirect() {
		return overrideRedirect;
	}

}
