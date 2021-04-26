package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X circulate request event. */
public final class CirculateRequest extends Event {

	private int parentWindowID;
	private int windowID;
	private int place;

	public CirculateRequest(Display display, ResponseInputStream in) {
		super(display, in);
		parentWindowID = in.readInt32();
		windowID = in.readInt32();
		in.skip(4); // Unused.
		place = in.readInt8();
		in.skip(15);
	}

	public int getParentWindowID() {
		return parentWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public int getPlace() {
		return place;
	}

}
