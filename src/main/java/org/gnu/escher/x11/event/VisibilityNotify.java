package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.enums.VisibilityState;

/** X visibility notify event. */
public final class VisibilityNotify extends Event {

	private int windowID;
	private VisibilityState state;

	public VisibilityNotify(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		state = VisibilityState.getByCode(in.readInt8());
		in.skip(23);
	}

	public int getWindowID() {
		return windowID;
	}

	public VisibilityState getState() {
		return state;
	}
}
