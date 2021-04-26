package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X selection notify event. */
public final class SelectionNotify extends Event {

	private int time;
	private int requestorWindowID;
	private int selectionAtomID;
	private int targetAtomID;
	private int propertyAtomID;

	public SelectionNotify(Display display, ResponseInputStream in) {
		super(display, in);
		time = in.readInt32();
		requestorWindowID = in.readInt32();
		selectionAtomID = in.readInt32();
		targetAtomID = in.readInt32();
		propertyAtomID = in.readInt32();
		in.skip(8);
	}

	public int getTime() {
		return time;
	}

	public int getRequestorWindowID() {
		return requestorWindowID;
	}

	public int getSelectionAtomID() {
		return selectionAtomID;
	}

	public int getTargetAtomID() {
		return targetAtomID;
	}

	public int getPropertyAtomID() {
		return propertyAtomID;
	}
}
