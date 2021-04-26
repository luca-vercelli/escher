package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X button release event. */
public final class ButtonRelease extends InputEvent {

	public ButtonRelease(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
