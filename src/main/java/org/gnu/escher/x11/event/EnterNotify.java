package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;


/** X enter notify event. */
public final class EnterNotify extends InputEvent {

  public EnterNotify (Display display, ResponseInputStream in) {
    super(display, in);
  }
}
