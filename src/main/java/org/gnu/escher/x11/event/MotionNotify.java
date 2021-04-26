package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X motion notify event. */
public final class MotionNotify extends InputEvent {

	public MotionNotify(Display display, ResponseInputStream in) {
		super(display, in);
	}

}
