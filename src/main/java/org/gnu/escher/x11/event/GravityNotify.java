package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X gravity notify event. */
public final class GravityNotify extends Event {

	private int eventWindowID;
	private int windowID;

	private int x;
	private int y;

	public GravityNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		in.skip(16);
	}

	public int getEventWindowID() {
		return eventWindowID;
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
}
