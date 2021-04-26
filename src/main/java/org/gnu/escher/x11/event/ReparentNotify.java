package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X reparent notify event. */
public final class ReparentNotify extends Event {

	private int eventWindowID;
	private int windowID;
	private int parentWindowID;
	private int x;
	private int y;
	private boolean overrideRedirect;

	public ReparentNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		parentWindowID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		overrideRedirect = in.readBool();
		in.skip(11);
	}

	public int getEventWindowID() {
		return eventWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public int getParentWindowID() {
		return parentWindowID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isOverrideRedirect() {
		return overrideRedirect;
	}
}
