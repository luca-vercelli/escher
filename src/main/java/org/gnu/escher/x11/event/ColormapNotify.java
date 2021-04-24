package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.ResponseInputStream;

/** X colormap notify event. */
public final class ColormapNotify extends Event {

	private int windowID;
	private int colormapID;
	private boolean isNew;
	private int state;

	public ColormapNotify(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		colormapID = in.readInt32();
		isNew = in.readBool();
		state = in.readInt8();
		in.skip(18);
	}

	public int getWindowID() {
		return windowID;
	}

	public int getColormapID() {
		return colormapID;
	}

	public boolean isNew() {
		return isNew;
	}

	public int getState() {
		return state;
	}

}
