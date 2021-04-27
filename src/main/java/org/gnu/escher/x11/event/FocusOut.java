package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X focus out event. */
public final class FocusOut extends FocusEvent {

	public FocusOut(Display display, ResponseInputStream in) {
		super(display, in);
	}
}
