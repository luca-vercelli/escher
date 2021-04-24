package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X unmap notify event. */
public final class UnmapNotify extends Event {

	private int eventWindowID;
	private int windowID;
	private boolean fromConfigure;

	/** Reading. */
	public UnmapNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		fromConfigure = in.readBool();
		in.skip(19);
	}

	public int getEventWindowID() {
		return eventWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public boolean isFromConfigure() {
		return fromConfigure;
	}
}
