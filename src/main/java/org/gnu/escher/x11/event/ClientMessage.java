package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Atom;
import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X client message event. */
public final class ClientMessage extends Event {
	private int windowID;
	private int typeAtomID;
	private byte[] data;

	/** Reading. */
	public ClientMessage(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		typeAtomID = in.readInt32();
		data = new byte[20]; // content depends on message type
		in.readData(data);
	}

	// -- reading

	public int getFormat() {
		return getDetail();
	}

	public int getTypeId() {
		return typeAtomID;
	}

	/**
	 * Return bytes 0-3 of data as int
	 * @return
	 */
	public int wm_data() {
		return ((((int) data[0]) & 0xff) << 24 | (((int) data[1]) & 0xff) << 16 | (((int) data[2]) & 0xff) << 8
				| (((int) data[3]) & 0xff));
	}

	/**
	 * Return bytes 4-7 of data as int
	 * @return
	 */
	public int wm_time() {
		return ((((int) data[4]) & 0xff) << 24 | (((int) data[5]) & 0xff) << 16 | (((int) data[6]) & 0xff) << 8
				| (((int) data[7]) & 0xff));
	}

	/**
	 * Is this a "Close window" message?
	 * @return
	 */
	// todo seems to return if this message is for a deleted window this is not
	// obvious from the method name
	public boolean isDeleteWindow() {
		Atom wm_protocols = (Atom) Atom.intern(getDisplay(), "WM_PROTOCOLS");
		Atom wm_delete_window = (Atom) Atom.intern(getDisplay(), "WM_DELETE_WINDOW");

		return getFormat() == 32 && getType() == wm_protocols && wm_data() == wm_delete_window.getId();
	}

	public Atom getType() {
		return (Atom) Atom.intern(getDisplay(), getTypeId(), true);
	}

	public int getWindowID() {
		return windowID;
	}

}
