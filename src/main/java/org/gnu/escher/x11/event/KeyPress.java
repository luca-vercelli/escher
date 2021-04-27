package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.enums.EventCode;

/**
 * X key press event.
 */
public final class KeyPress extends InputEvent {

	public KeyPress(Display display) {
		super(display, EventCode.KEY_PRESS);
	}

	/**
	 * Reads a KeyPress event from the input stream.
	 *
	 * @param display the display from which this event originated.
	 * @param in      the input stream to read from
	 */
	public KeyPress(Display display, ResponseInputStream in) {
		super(display, in);
	}

}
