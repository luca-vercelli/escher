package org.gnu.escher.x11.core;

import org.gnu.escher.x11.enums.ErrorCode;

/**
 * X server core error.
 *
 * This class is based on <code>Error</code> instead of <code>Exception</code>
 * or <code>RuntimeException</code> because X server error should be regarded as
 * fatal.
 *
 * Instead of setting error handler with <code>XSetErrorHandler</code> in C
 * programming, we should utilize the exception facility of Java programming.
 */
public class X11ServiceException extends X11ClientException {

	private static final long serialVersionUID = 323920206005015410L;

	public ErrorCode code;
	public int seq_no, bad, minor_opcode, major_opcode;

	public static final String[] OPCODE_STRINGS = { null, // 0
			"CreateWindow", // 1
			"ChangeWindowAttributes", // 2
			"GetWindowAttributes", // 3
			"DestroyWindow", // 4
			"DestroySubwindows", // 5
			"ChangeSaveSet", // 6
			"ReparentWindow", // 7
			"MapWindow", // 8
			"MapSubwindows", // 9
			"UnmapWindow", // 10
			"UnmapSubwindows", // 11
			"ConfigureWindow", // 12
			"CirculateWindow", // 13
			"GetGeometry", // 14
			"QueryTree", // 15
			"InternAtom", // 16
			"GetAtomName", // 17
			"ChangeProperty", // 18
			"DeleteProperty", // 19
			"GetProperty", // 20
			"ListProperties", // 21
			"SetSelectionOwner", // 22
			"GetSelectionOwner", // 23
			"ConvertSelection", // 24
			"SendEvent", // 25
			"GrabPointer", // 26
			"UngrabPointer", // 27
			"GrabButton", // 28
			"UngrabButton", // 29
			"ChangeActivePointerGrab", // 30
			"GrabKeyboard", // 31
			"UngrabKeyboard", // 32
			"GrabKey", // 33
			"UngrabKey", // 34
			"AllowEvents", // 35
			"GrabServer", // 36
			"UngrabServer", // 37
			"QueryPointer", // 38
			"GetMotionEvents", // 39
			"TranslateCoords", // 40
			"WarpPointer", // 41
			"SetInputFocus", // 42
			"GetInputFocus", // 43
			"QueryKeymap", // 44
			"OpenFont", // 45
			"CloseFont", // 46
			"QueryFont", // 47
			"QueryTextExtents", // 48
			"ListFonts", // 49
			"ListFontsWithInfo", // 50
			"SetFontPath", // 51
			"GetFontPath", // 52
			"CreatePixmap", // 53
			"FreePixmap", // 54
			"CreateGC", // 55
			"ChangeGC", // 56
			"CopyGC", // 57
			"SetDashes", // 58
			"SetClipRectangles", // 59
			"FreeGC", // 60
			"ClearArea", // 61
			"CopyArea", // 62
			"CopyPlane", // 63
			"PolyPoint", // 64
			"PolyLine", // 65
			"PolySegment", // 66
			"PolyRectangle", // 67
			"PolyArc", // 68
			"FillPoly", // 69
			"PolyFillRectangle", // 70
			"PolyFillArc", // 71
			"PutImage", // 72
			"GetImage", // 73
			"PolyText8", // 74
			"PolyText16", // 75
			"ImageText8", // 76
			"ImageText16", // 77
			"CreateColormap", // 78
			"FreeColormap", // 79
			"CopyColormapAndFree", // 80
			"InstallColormap", // 81
			"UninstallColormap", // 82
			"ListInstalledColormaps", // 83
			"AllocColor", // 84
			"AllocNamedColor", // 85
			"AllocColorCells", // 86
			"AllocColorPlanes", // 87
			"FreeColors", // 88
			"StoreColors", // 89
			"StoreNamedColor", // 90
			"QueryColors", // 91
			"LookupColor", // 92
			"CreateCursor", // 93
			"CreateGlyphCursor", // 94
			"FreeCursor", // 95
			"RecolorCursor", // 96
			"QueryBestSize", // 97
			"QueryExtension", // 98
			"ListExtensions", // 99
			"ChangeKeyboardMapping", // 100
			"GetKeyboardMapping", // 101
			"ChangeKeyboardControl", // 102
			"GetKeyboardControl", // 103
			"Bell", // 104
			"ChangePointerControl", // 105
			"GetPointerControl", // 106
			"SetScreenSaver", // 107
			"GetScreenSaver", // 108
			"ChangeHosts", // 109
			"ListHosts", // 110
			"SetAccessControl", // 111
			"SetCloseDownMode", // 112
			"KillClient", // 113
			"RotateProperties", // 114
			"ForceScreenSaver", // 115
			"SetPointerMapping", // 116
			"GetPointerMapping", // 117
			"SetModifierMapping", // 118
			"GetModifierMapping", // 119
			null, // 120
			null, // 121
			null, // 122
			null, // 123
			null, // 124
			null, // 125
			null, // 126
			"NoOperation" // 127
	};

	// <--- Constructors ----> //

	public X11ServiceException(String s) {
		super(s);
	}

	public X11ServiceException(Display display, String error_string, ErrorCode code, int seq_no, int bad,
			int minor_opcode, int major_opcode) {

		super(init(display, error_string, code, seq_no, bad, minor_opcode, major_opcode));
		this.code = code;
		this.seq_no = seq_no;
		this.bad = bad;
		this.minor_opcode = minor_opcode;
		this.major_opcode = major_opcode;
	}

	// <---- Methods ---> //
	public static String init(Display display, String error_string, ErrorCode code, int seq_no, int bad,
			int minor_opcode, int major_opcode) {

		// -- code
		String code_string = "\n  code: " + code.getErrorId() + " " + error_string;

		// -- sequence number
		String seq_no_string = "\n  sequence-number: " + seq_no;

		// -- bad

		String bad_string = "";
		if (code == ErrorCode.BAD_COLORMAP || code == ErrorCode.BAD_CURSOR || code == ErrorCode.BAD_DRAWABLE
				|| code == ErrorCode.BAD_FONT || code == ErrorCode.BAD_GC || code == ErrorCode.BAD_ID_CHOICE
				|| code == ErrorCode.BAD_PIXMAP || code == ErrorCode.BAD_WINDOW) {

			Object bad_object = display.getResources().get(new Integer(bad));
			bad_string = bad_object == null ? "\n  bad-id: " + bad : "\n  bad-object: " + bad_object;

		} else if (code == ErrorCode.BAD_ATOM) {
			Object bad_atom = display.getAtom(bad);
			bad_string = bad_atom == null ? "\n  bad-atom-id: " + bad : "\n  bad-atom: " + bad_atom;

		} else if (code == ErrorCode.BAD_VALUE) {
			bad_string = "\n  bad-value: " + bad;
		} else if (code == ErrorCode.UNKNOWN_ERROR) {
			bad_string = "\n unkown-error: " + bad;
		}

		// -- major opcode

		String major_opcode_string = "\n  major-opcode: " + major_opcode + " "
				+ (major_opcode < 128 ? OPCODE_STRINGS[major_opcode]
						: display.extensionOpcodeStrings[major_opcode - 128]);

		// -- minor opcode

		String minor_opcode_string = major_opcode < 128 ? ""
				: "\n  minor-opcode: " + minor_opcode + " "
						+ display.extensionMinorOpcodeStrings[major_opcode - 128][minor_opcode];

		// -- output

		return "#Error" + code_string + seq_no_string + bad_string + major_opcode_string + minor_opcode_string;
	}
}