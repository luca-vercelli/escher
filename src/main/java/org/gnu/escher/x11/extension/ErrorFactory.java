package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.X11ServiceException;

public interface ErrorFactory {
	X11ServiceException build(Display display, int code, int seqNumber, int bad, int minorOpcode, int majorOpcode);
}
