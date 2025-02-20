package org.gnu.escher.x11.enums;

import java.util.*;
import java.util.stream.*;

import static java.util.function.UnaryOperator.*;
import static java.util.stream.Collectors.*;

/**
 * Event Code List, got it from XMonad
 */
public enum EventCode {

	ERROR(0),
	NONE(1),

	// Input Device Events
	KEY_PRESS(2), //
	KEY_RELEASE(3), //
	BUTTON_PRESS(4), //
	BUTTON_RELEASE(5), //
	MOTION_NOTIFY(6), //

	// Pointer Window Events
	ENTER_NOTIFY(7), //
	LEAVE_NOTIFY(8), //

	// Input Focus Events
	FOCUS_IN(9), //
	FOCUS_OUT(10), //

	KEYMAP_NOTIFY(11), //
	EXPOSE(12), //
	GRAPHICS_EXPOSE(13), //
	NO_EXPOSE(14), //
	VISIBILITY_NOTIFY(15), //
	CREATE_NOTIFY(16), //
	DESTROY_NOTIFY(17), //
	UNMAP_NOTIFY(18), //
	MAP_NOTIFY(19), //
	MAP_REQUEST(20), //
	REPARENT_NOTIFY(21), //
	CONFIGURE_NOTIFY(22), //
	CONFIGURE_REQUEST(23), //
	GRAVITY_NOTIFY(24), //
	RESIZE_REQUEST(25), //
	CIRCULATE_NOTIFY(26), //
	CIRCULATE_REQUEST(27), //
	PROPERTY_NOTIFY(28), //
	SELECTION_CLEAR(29), //
	SELECTION_REQUEST(30), //
	SELECTION_NOTIFY(31), //
	COLORMAP_NOTIFY(32), //
	CLIENT_MESSAGE(33), //
	MAPPING_NOTIFY(34), //

	// Window closed, or "other"
	UNKNOWN(36);

	private static final Map<Integer, EventCode> fromCodes;

	static {
		fromCodes = Stream.of(values()).collect(toMap(EventCode::getCode, identity()));
	}

	private final int code;

	EventCode(int code) {
		this.code = code;
	}

	/**
	 * Returns {@link EventCode} for given code.
	 *
	 * @param code for event
	 * @return matching EventCode or LAST_EVENT if no match
	 */
	public static EventCode of(int code) {
		code = code & 0x7f; // remove synthetic mask
		EventCode event = fromCodes.get(code);
		return (event != null) ? event : UNKNOWN;
	}

	/**
	 * Returns event code matching code and mask. May return null.
	 *
	 * @param mask
	 * @return
	 */
	public EventCode and(int mask) {
		int andMask = this.getCode() & mask;
		return fromCodes.get(andMask);
	}

	public int getCode() {
		return code;
	}
}
