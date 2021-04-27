package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.enums.FocusMode;

public abstract class FocusEvent extends Event {

	private int eventWindowID;

	private FocusMode mode;

	public FocusEvent(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		mode = FocusMode.getByCode(in.readInt8());
		in.skip(23); // Unused.
	}

	public int getEventWindowID() {
		// Should really return the Window object here.
		return eventWindowID;
	}

	public FocusMode getMode() {
		return mode;
	}
}
