package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X keymap notify event. */
public final class KeymapNotify extends Event {

	private Display display;

	private int code;

	private byte[] keys;

	public KeymapNotify(Display display, ResponseInputStream in) {
		super(display);
		this.display = display;
		code = in.readInt8();
		keys = new byte[31];
		in.readData(keys);
	}

	public Display getDisplay() {
		return display;
	}

	public int getKeyCode() {
		return code;
	}

	public byte[] getKeys() {
		return keys;
	}

}
