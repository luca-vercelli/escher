package org.gnu.escher.x11.event;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.InputStreamObject;
import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.enums.EventCode;

/**
 * The base class for all X events.
 */
public abstract class Event implements InputStreamObject {

	private Display display;
	private EventCode code;
	private int detail;
	private int sequenceNumber;
	private int syntheticFlag;

	/**
	 * Creates an event without reading. This is used in subclasses that don't use
	 * the usual first 3 fields.
	 */
	Event(Display display) {
		this.display = display;
	}

	/**
	 * Reads the event from the input stream.
	 */
	public Event(Display display, ResponseInputStream in) {
		this.display = display;
		int firstByte = in.readInt8();
		syntheticFlag = firstByte >> 7;
		code = EventCode.of(firstByte & 0x7f);
		detail = in.readInt8();
		sequenceNumber = in.readInt16();
	}

	public Event(Display disp, EventCode c) {
		display = disp;
		code = c;
	}

	public String toString() {
		String class_name = "#" + getClass().getName();
		return class_name + " " + getCode();
	}

	/**
	 * Writes this event into a request. This is used in
	 * {@link org.gnu.escher.x11.resource.Window#sendEvent(boolean, int, Event)}.
	 *
	 * @param o the output stream to write to
	 */
	public void write(RequestOutputStream o) {
		o.writeInt8(code.getCode());
		o.writeInt8(detail);
		o.writeInt16(sequenceNumber); // Is this correct?

		// The remaining pieces must be written by the subclasses.
	}

	public Display getDisplay() {
		return display;
	}

	public EventCode getCode() {
		return code;
	}

	public int getDetail() {
		return detail;
	}

	public void setDetail(int detail) {
		this.detail = detail;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public int getSyntheticFlag() {
		return syntheticFlag;
	}

}
