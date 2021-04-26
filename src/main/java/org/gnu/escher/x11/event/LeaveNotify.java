package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X leave notify event. */
public final class LeaveNotify extends InputEvent {

	public LeaveNotify(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
