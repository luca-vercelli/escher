package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X focus in event. */
public final class FocusIn extends FocusEvent {

	public FocusIn(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
