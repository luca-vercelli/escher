package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;


/** X enter notify event. */
public final class EnterNotify extends InputEvent {

  public EnterNotify (Display display, ResponseInputStream in) {
    super(display, in);
  }
}
