package org.gnu.escher.x11.extension;


import org.gnu.escher.x11.*;

public interface ErrorFactory {
  X11ServiceException build (org.gnu.escher.x11.Display display, int code, int seqNumber, int bad,
                             int minorOpcode, int majorOpcode);
}
