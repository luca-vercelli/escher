package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X no exposure event. */
public final class NoExposure extends Event {

	private int drawableID;
	private int minorOpcode;
	private int majorOpcode;

	public NoExposure(Display display, ResponseInputStream in) {
		super(display, in);
		drawableID = in.readInt32();
		minorOpcode = in.readInt16();
		majorOpcode = in.readInt8();
		in.skip(21);
	}

	public int getDrawableID() {
		return drawableID;
	}

	public int getMinorOpcode() {
		return minorOpcode;
	}

	public int getMajorOpcode() {
		return majorOpcode;
	}
}
