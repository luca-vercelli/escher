package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.enums.Place;

/** X circulate notify event. */
public final class CirculateNotify extends Event {

	private int eventWindowID;
	private int windowID;
	private Place place;

	public CirculateNotify(Display display, ResponseInputStream in) {
		super(display, in);
		eventWindowID = in.readInt32();
		windowID = in.readInt32();
		in.skip(4); // Unused.
		place = Place.getByCode(in.readInt8());
		in.skip(15);
	}

	public int getEventWindowID() {
		return eventWindowID;
	}

	public int getWindowID() {
		return windowID;
	}

	public Place getPlace() {
		return place;
	}

}
