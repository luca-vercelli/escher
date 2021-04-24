package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X resize request event. */
public final class ResizeRequest extends Event {

	private int windowID;
	private int width;
	private int height;

	public ResizeRequest(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		width = in.readInt16();
		height = in.readInt16();
		in.skip(20);
	}

	// -- reading

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	/**
	 * Returns the window ID of the resize request.
	 *
	 * @return the window ID of the resize request
	 */
	public int getWindowId() {
		return windowID;
	}
}
