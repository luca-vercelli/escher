package org.gnu.escher.x11.core;

/**
 * Base exception class for any errors that occur when sending request to X11 or
 * receiving results from X11.
 */
public class X11ClientException extends RuntimeException {

	private static final long serialVersionUID = 5248084422720501682L;

	public X11ClientException(Throwable cause) {
		super(cause);
	}

	public X11ClientException(String message, Throwable cause) {
		super(message, cause);
	}

	public X11ClientException(String message) {
		super(message);
	}
}
