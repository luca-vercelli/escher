package org.gnu.escher.x11.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum represents an Error Code from a request with X server.
 */
public enum ErrorCode {
	SUCCESS(0, "SUCCESS: everything okay"),

	/**
	 * The major or minor opcode does not specify a valid request. This usually is
	 * an Xlib or server error.
	 */
	BAD_REQUEST(1, "BAD_REQUEST: bad request code"),

	/**
	 * Some numeric value falls outside of the range of values accepted by the
	 * request. Unless a specific range is specified for an argument, the full range
	 * defined by the argument's type is accepted. Any argument defined as a set of
	 * alternatives typically can generate this error (due to the encoding).
	 */
	BAD_VALUE(2, "BAD_VALUE: parameter out of range"),

	/**
	 * A value for a window argument does not name a defined window.
	 */
	BAD_WINDOW(3, "BAD_WINDOW: parameter not a window"),

	/**
	 * A value for a pixmap argument does not name a defined pixmap.
	 */
	BAD_PIXMAP(4, "BAD_PIXMAP: parameter not a pixmap"),

	/**
	 * A value for an Atom argument does not name a defined Atom.
	 */
	BAD_ATOM(5, "BAD_ATOM: parameter not an atom"),

	/**
	 * A value for a cursor argument does not name a defined cursor.
	 */
	BAD_CURSOR(6, "BAD_CURSOR: parameter not a cursor"),

	/**
	 * A value for a font argument does not name a defined font (or, in some cases,
	 * GContext).
	 */
	BAD_FONT(7, "BAD_FONT: parameter not a font"),

	/**
	 * In a graphics request, the root and depth of the graphics context do not
	 * match those of the drawable. An <em>InputOnly</em> window is used as a
	 * drawable. Some argument or pair of arguments has the correct type and range,
	 * but it fails to match in some other way required by the request. An
	 * <em>InputOnly</em> window lacks this attribute.
	 */
	BAD_MATCH(8, "BAD_MATCH: parameter mismatch"),

	/**
	 * A value for a drawable argument does not name a defined window or pixmap.
	 */
	BAD_DRAWABLE(9, "BAD_DRAWABLE: parameter not a pixmap or a window"),

	/**
	 * A client attempts to grab a key/button combination already grabbed by another
	 * client.
	 *
	 * <p>
	 * A client attempts to free a colormap entry that it had not already allocated
	 * or to free an entry in a colormap that was created with all entries writable.
	 *
	 * <p>
	 * A client attempts to store into a read-only or unallocated colormap entry.
	 *
	 * <p>
	 * A client attempts to modify the access control list from other than the local
	 * (or otherwise authorized) host.
	 *
	 * <p>
	 * A client attempts to select an event type that another client has already
	 * selected.
	 */
	BAD_ACCESS(10, "BAD_ACCESS: operation right denied"),

	/**
	 * The server fails to allocate the requested resource. Note that the explicit
	 * listing of <em>BadAlloc</em> errors in requests only covers allocation errors
	 * at a very coarse level and is not intended to (nor can it in practice hope
	 * to) cover all cases of a server running out of allocation space in the middle
	 * of service. The semantics when a server runs out of allocation space are left
	 * unspecified, but a server may generate a <em>BadAlloc</em> error on any
	 * request for this reason, and clients should be prepared to receive such
	 * errors and handle or discard them.
	 */
	BAD_ALLOC(11, "BAD_ALLOC: insufficient resources"),

	/**
	 * A value for a Colormap argument does not name a defined Colormap.
	 */
	BAD_COLORMAP(12, "BAD_COLORMAP: parameter not a colormap"),

	/**
	 * A value for a <em>GContext</em> argument does not name a defined
	 * <em>GContext</em>.
	 */
	BAD_GC(13, "BAD_GC: parameter not a GC"),

	/**
	 * The value chosen for a resource identifier either is not included in the
	 * range assigned to the client or is already in use. Under normal
	 * circumstances, this cannot occur and should be considered a server or Xlib
	 * error.
	 */
	BAD_ID_CHOICE(14, "BAD_ID_CHOICE: id choice not in range or already used"),

	/**
	 * A font or color of the specified name does not exist.
	 */
	BAD_NAME(15, "BAD_NAME: font or color name non-existent"),

	/**
	 * The length of a request is shorter or longer than that required to contain
	 * the arguments. This is an internal Xlib or server error. The length of a
	 * request exceeds the maximum length accepted by the server.
	 */
	BAD_LENGTH(16, "BAD_LENGTH: request length incorrect"),

	/**
	 * The server does not implement some aspect of the request. A server that
	 * generates this error for a core request is deficient. As such, this error is
	 * not listed for any of the requests, but clients should be prepared to receive
	 * such errors and handle or discard them.
	 */
	BAD_IMPLEMENTATION(17, "BAD_IMPLEMENTATION: server defective"),

	/**
	 * Unkown Error Code
	 */
	UNKNOWN_ERROR(-1, "UNKNOWN ERROR: unknown error");

	private int errorID;
	private String errorMsg;
	private static Map<Integer, ErrorCode> errorCodes;

	private static Map<Integer, ErrorCode> getErrorCodes() {
		if (errorCodes == null) {
			errorCodes = new HashMap<Integer, ErrorCode>();
			for (ErrorCode code : ErrorCode.values())
				errorCodes.put(code.errorID, code);
		}

		return errorCodes;
	}

	public static ErrorCode getError(int id) {
		ErrorCode error = getErrorCodes().get(id);
		return (error == null) ? error : UNKNOWN_ERROR;
	}

	ErrorCode(int id, String errorMS) {
		this.errorID = id;
		this.errorMsg = errorMS;
	}

	public int getErrorId() {
		return this.errorID;
	}

	public String getErrorMessage() {
		return this.errorMsg;
	}

}