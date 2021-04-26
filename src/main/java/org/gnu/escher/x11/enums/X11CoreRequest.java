package org.gnu.escher.x11.enums;

import org.gnu.escher.x11.X11Command;

/**
 * Enum class describing X11 Core commands.
 * 
 * Each command is associated with a Command ID (<code>opcode</code>) and a
 * <code>length</code> field.
 * 
 * The name of each field reflect the name defined in the "X Protocol Reference
 * Manual, for version X 11" manual.
 * 
 * See <a href='http://www.x.org/wiki/'> http://www.x.org/wiki/</a> for details.
 * 
 * @author Mario Torre neugens@aicas.com
 */
public enum X11CoreRequest implements X11Command {

	// TODO fill this, -1 means "I had no time to write this yet"

	// in Window
	CreateWindow(1, 8), //
	ChangeWindowAttributes(2, 3), //
	GetWindowAttributes(3, 2), //
	DestroyWindow(4, 2), //
	DestroySubwindows(5, 2), //
	ChangeSaveSet(6, 2), //
	ReparentWindow(7, 4), //
	MapWindow(8, 2), //
	MapSubwindows(9, 2), //
	UnmapWindow(10, 2), //
	UnmapSubwindows(11, 2), //
	ConfigureWindow(12, 3), //
	CirculateWindow(13, 2), //
	GetGeometry(14, -1), // in Drawable ?!?
	QueryTree(15, 2), //
	InternAtom(16, 2), //
	GetAtomName(17, 2), //
	ChangeProperty(18, 6), //
	DeleteProperty(19, -1), //
	GetProperty(20, -1), //
	RotateProperties(114, -1), //
	ListProperties(21, -1), //
	SetSelectionOwner(22, -1), //
	GetSelectionOwner(23, -1), // in Display
	ConvertSelection(24, -1), //
	SendEvent(25, -1), //
	GrabPointer(26, -1), //
	UngrabPointer(27, -1), //
	GrabButton(28, -1), //
	UngrabButton(29, -1), //
	ChangeActivePointerGrab(30, 2), // in Input
	GrabKeyboard(31, 4), //
	UngrabKeyboard(32, 2), // in Input
	GrabKey(33, 4), //
	UngrabKey(34, -1), //
	AllowEvents(35, 2), // in Input
	GrabServer(36, -1), // in Display
	UngrabServer(37, -1), // in Display
	QueryPointer(38, 2), //
	GetMotionEvents(39, 4), //
	TranslateCoordinates(40, -1), //
	WarpPointer(41, -1), //
	SetInputFocus(42, 3), //
	GetInputFocus(43, 1), // in Input
	QueryKeymap(44, 1), // in Input

	// in Font
	OpenFont(45, 3), //
	CloseFont(46, 2), //

	// in Fontable
	QueryFont(47, 2), //
	QueryTextExtents(48, 2), //

	// in Display
	ListFonts(49, 2), //
	ListFontsWithInfo(50, -1), // NOT IMPLEMENTED
	SetFontPath(51, 2), //
	GetFontPath(52, 1), //

	// in Pixmap
	CreatePixmap(53, 4), //
	FreePixmap(54, 2), //
	
	// in GC
	CreateGC(55, 4), //
	ChangeGC(56, 0), //
	CopyGC(57, 4), //
	SetDashes(58, 3), //
	SetClipRectangles(59, 3), //
	FreeGC(60, 2), //

	ClearArea(61, 4), // in Window?!?

	// in Drawable
	CopyArea(62, 7), //
	CopyPlane(63, -1), //
	PolyPoint(64, -1), //
	PolyLine(65, -1), //
	PolySegment(66, -1), //
	PolyRectangle(67, -1), //
	PolyArc(68, -1), //
	FillPoly(69, -1), //
	PolyFillRectangle(70, -1), //
	PolyFillArc(71, -1), //
	PutImage(72, -1), //
	GetImage(73, 5), //
	PolyText8(74, -1), //
	PolyText16(75, -1), //
	ImageText8(76, -1), //
	ImageText16(77, -1), //

	// in ColorMap
	CreateColormap(78, -1), //
	FreeColormap(79, -1), //
	CopyColormapAndFree(80, -1), //
	InstallColormap(81, -1), //
	UninstallColormap(82, -1), //
	ListInstalledColormaps(83, 2), // this is in Window !?!?
	AllocColor(84, -1), //
	AllocNamedColor(85, -1), //
	AllocColorCells(86, -1), //
	AllocColorPlanes(87, -1), //
	FreeColors(88, -1), //
	StoreColors(89, -1), //
	StoreNamedColor(90, -1), //
	QueryColors(91, -1), //
	LookupColor(92, -1), //

	// in Cursor
	CreateCursor(93, 8), //
	CreateGlyphCursor(94, 8), //
	FreeCursor(95, 2), //
	RecolorCursor(96, 5), //

	QueryBestSize(97, -1), // in Drawable ?!
	QueryExtension(98, -1), // in Display
	ListExtensions(99, -1), // in Display

	// in Input
	SetModifierMapping(118, 2), //
	GetModifierMapping(119, -1), //
	ChangeKeyboardMapping(100, -1), //
	GetKeyboardMapping(101, -1), //
	ChangeKeyboardControl(102, -1), //
	GetKeyboardControl(103, 1), //
	SetPointerMapping(116, 1), //
	GetPointerMapping(117, 1), //
	ChangePointerControl(105, 3), //
	GetPointerControl(106, 1), //

	// in Display
	Bell(104, -1), //
	SetScreenSaver(107, -1), //
	GetScreenSaver(108, -1), //
	ForceScreenSaver(115, -1), //
	ChangeHosts(109, -1), //
	ListHosts(110, -1), //
	SetAccessControl(111, -1), //
	SetCloseDownMode(112, -1), //
	KillClient(113, -1), //

	NoOperation(127, -1);

	private final int opcode;
	private final int length;

	X11CoreRequest(int opcode, int length) {
		this.opcode = opcode;
		this.length = length;
	}

	@Override
	public int getBaseLength() {
		return this.length;
	}

	@Override
	public int getOpcode() {
		return this.opcode;
	}

}
