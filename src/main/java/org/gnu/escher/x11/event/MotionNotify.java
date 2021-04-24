package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X motion notify event. */
public final class MotionNotify extends InputEvent {

	public MotionNotify(Display display, ResponseInputStream in) {
		super(display, in);
	}

}
