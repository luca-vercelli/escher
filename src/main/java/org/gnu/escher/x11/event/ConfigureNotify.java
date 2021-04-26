package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.geometric.Rectangle;

/** X configure notify event. */
public final class ConfigureNotify extends Event {

	private int eventWindowID;
	private int windowID;
	private int aboveSiblingID;

	private int x;
	private int y;
	private int width;
	private int height;

	private int borderWidth;
	private boolean overrideRedirect;

	/** Reading. */
	public ConfigureNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		aboveSiblingID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		width = in.readInt16();
		height = in.readInt16();
		borderWidth = in.readInt16();
		overrideRedirect = in.readBool();
		in.skip(5);
	}

	// -- reading

	public int getAboveSiblingID() {
		return aboveSiblingID;
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

	public Rectangle rectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public int getEventWindowID() {
		return eventWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public boolean isOverrideRedirect() {
		return overrideRedirect;
	}

}
