package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X leave notify event. */
public final class LeaveNotify extends InputEvent {

	public LeaveNotify(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
