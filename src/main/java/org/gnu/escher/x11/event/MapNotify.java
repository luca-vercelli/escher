package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X map notify event. */
public final class MapNotify extends Event {

	private int eventWindowID;
	private int windowID;
	private boolean overrideRedirect;

	public MapNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		overrideRedirect = in.readBool();
		in.skip(19);
	}

	public int getEventWindowID() {
		return eventWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public boolean isOverrideRedirect() {
		return overrideRedirect;
	}
}
