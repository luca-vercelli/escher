package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Atom;
import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.enums.PropertyState;

/** X property notify event. */
public final class PropertyNotify extends Event {

	private int windowID;
	private int atomID;
	private int time;
	private PropertyState state;

	public PropertyNotify(Display display, ResponseInputStream in) {
		super(display, in);
		windowID = in.readInt32();
		atomID = in.readInt32();
		time = in.readInt32();
		state = PropertyState.getByCode(in.readInt8());
		in.skip(15);
	}

	/**
	 * @deprecated use {@link #getAtom(Display)} instead.
	 * @param display
	 * @return
	 */
	@Deprecated
	public Atom atom(Display display) {
		return (Atom) Atom.intern(display, atomID, true);
	}

	public Atom getAtom(Display display) {

		return atom(display);
	}

	public int getAtomID() {

		return this.atomID;
	}

	public int getWindowID() {

		return this.windowID;
	}

	public int getTime() {

		return this.time;
	}

	public PropertyState getState() {

		return this.state;
	}
}
