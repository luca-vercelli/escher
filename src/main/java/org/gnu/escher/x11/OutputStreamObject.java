package org.gnu.escher.x11;

import org.gnu.escher.x11.core.RequestOutputStream;

/**
 * Denote objects that can be sent via ResponseOutputStream
 */
public interface OutputStreamObject {

	public void write(RequestOutputStream o);

}
