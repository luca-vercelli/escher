package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.event.Event;

public interface EventFactory {
	Event build(Display display, ResponseInputStream i, int code);
}
