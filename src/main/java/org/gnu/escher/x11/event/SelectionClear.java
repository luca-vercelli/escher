package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X selection clear event. */
public final class SelectionClear extends Event {

	private int time;
	private int ownerID;
	private int selectionAtomID;

	public SelectionClear(Display display, ResponseInputStream in) {
		super(display, in);
		time = in.readInt32();
		ownerID = in.readInt32();
		selectionAtomID = in.readInt32();
		in.skip(16);
	}

	public int getTime() {
		return time;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public int getSelectionAtomID() {
		return selectionAtomID;
	}
}
