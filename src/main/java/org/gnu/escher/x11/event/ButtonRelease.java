package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X button release event. */
public final class ButtonRelease extends InputEvent {

	public ButtonRelease(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
