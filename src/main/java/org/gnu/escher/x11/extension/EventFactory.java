package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.ResponseInputStream;


public interface EventFactory {
  org.gnu.escher.x11.event.Event build (org.gnu.escher.x11.Display display, ResponseInputStream i,
                             int code); 
}
