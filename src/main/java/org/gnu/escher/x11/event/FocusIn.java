package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X focus in event. */
public final class FocusIn extends FocusEvent {

	public FocusIn(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
