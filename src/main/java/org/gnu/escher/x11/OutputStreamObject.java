package org.gnu.escher.x11;

/**
 * Denote objects that can be sent via ResponseOutputStream
 */
public interface OutputStreamObject {

	public void write(RequestOutputStream o);

}
