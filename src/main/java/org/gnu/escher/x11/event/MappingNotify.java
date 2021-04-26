package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;

/** X mapping notify event. */
public final class MappingNotify extends Event {

	private int request;
	private int firstKeycode;
	private int count;

	public MappingNotify(Display display, ResponseInputStream in) {
		super(display, in);
		request = in.readInt8();
		firstKeycode = in.readInt8();
		count = in.readInt8();
		in.skip(25);
	}

	public int getRequest() {
		return request;
	}

	public int getFirstKeycode() {
		return firstKeycode;
	}

	public int getCount() {
		return count;
	}
}
