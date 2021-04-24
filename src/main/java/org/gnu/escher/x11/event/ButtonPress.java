package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X button press event. */
public final class ButtonPress extends InputEvent {

	public ButtonPress(Display display, ResponseInputStream in) {
		super(display, in);
	}

	public ButtonPress(Display display) {
		super(display, EventCode.BUTTON_PRESS);
	}
}
