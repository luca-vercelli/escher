package org.gnu.escher.x11.extension;


import org.gnu.escher.x11.*;

public class NotFoundException extends X11ClientException {
  public NotFoundException(String s) { super(s); }
}
