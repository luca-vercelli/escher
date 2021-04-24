package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.*;

public class ExtensionNotFoundException extends X11ClientException {

	private static final long serialVersionUID = -7076310859892141368L;

	public ExtensionNotFoundException(String s) {
		super(s);
	}
}
