package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X map request event. */
public final class MapRequest extends Event {

	private int parentWindowID;
	private int windowID;

	public MapRequest(Display display, ResponseInputStream in) {
		super(display, in);
		parentWindowID = in.readInt32();
		windowID = in.readInt32();
		in.skip(20);
	}

	public int getParentWindowID() {
		return parentWindowID;
	}

	public int getWindowID() {
		return windowID;
	}
}
