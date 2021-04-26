package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X key release event. */
public final class KeyRelease extends InputEvent {

	/**
	 * Reads a KeyRelease event from the input stream.
	 *
	 * @param display the display from which this event originated
	 * @param in      the input stream
	 */
	public KeyRelease(Display display, ResponseInputStream in) {
		super(display, in);
	}

}
