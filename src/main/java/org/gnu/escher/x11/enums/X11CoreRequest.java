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

	CreateWindow(1, 8), //
	ChangeWindowAttributes(2, 3), //
	GetWindowAttributes(3, 2), //
	DestroyWindow(4, -1), //
	DestroySubwindows(5, -1), //
	ChangeSaveSet(6, -1), //
	ReparentWindow(7, -1), //
	MapWindow(8, -1), //
	MapSubwindows(9, -1), //
	UnmapWindow(10, -1), //
	UnmapSubwindows(11, -1), //
	ConfigureWindow(12, -1), //
	CirculateWindow(13, -1), //
	GetGeometry(14, -1), //
	QueryTree(15, -1), //
	InternAtom(16, 2), //
	GetAtomName(17, 2), //
	ChangeProperty(18, -1), //
	DeleteProperty(19, -1), //
	GetProperty(20, -1), //
	RotateProperties(114, -1), //
	ListProperties(21, -1), //
	SetSelectionOwner(22, -1), //
	GetSelectionOwner(23, -1), //
	ConvertSelection(24, -1), //
	SendEvent(25, -1), //
	GrabPointer(26, -1), //
	UngrabPointer(27, -1), //
	GrabButton(28, -1), //
	UngrabButton(29, -1), //
	ChangeActivePointerGrab(30, -1), //
	GrabKeyboard(31, 4), //
	UngrabKeyboard(32, -1), //
	GrabKey(33, 4), //
	UngrabKey(34, -1), //
	AllowEvents(35, -1), //
	GrabServer(36, -1), //
	UngrabServer(37, -1), //
	QueryPointer(38, 2), //
	GetMotionEvents(39, 4), //
	TranslateCoordinates(40, -1), //
	WarpPointer(41, -1), //
	SetInputFocus(42, 3), //
	GetInputFocus(43, -1), //
	QueryKeymap(44, -1), //
	OpenFont(45, -1), //
	CloseFont(46, -1), //
	QueryFont(47, -1), //
	QueryTextExtents(48, -1), //
	ListFonts(49, -1), //
	ListFontsWithInfo(50, -1), //
	SetFontPath(51, -1), //
	GetFontPath(52, -1), //
	CreatePixmap(53, -1), //
	FreePixmap(54, -1), //
	CreateGC(55, -1), //
	ChangeGC(56, -1), //
	CopyGC(57, -1), //
	SetDashes(58, -1), //
	SetClipRectangles(59, -1), //
	FreeGC(60, -1), //
	ClearArea(61, -1), //
	CopyArea(62, -1), //
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
	CreateColormap(78, -1), //
	FreeColormap(79, -1), //
	CopyColormapAndFree(80, -1), //
	InstallColormap(81, -1), //
	UninstallColormap(82, -1), //
	ListInstalledColormaps(83, -1), //
	AllocColor(84, -1), //
	AllocNamedColor(85, -1), //
	AllocColorCells(86, -1), //
	AllocColorPlanes(87, -1), //
	FreeColors(88, -1), //
	StoreColors(89, -1), //
	StoreNamedColor(90, -1), //
	QueryColors(91, -1), //
	LookupColor(92, -1), //
	CreateCursor(93, -1), //
	CreateGlyphCursor(94, -1), //
	FreeCursor(95, -1), //
	RecolorCursor(96, -1), //
	QueryBestSize(97, -1), //
	QueryExtension(98, -1), //
	ListExtensions(99, -1), //
	SetModifierMapping(118, -1), //
	GetModifierMapping(119, -1), //
	ChangeKeyboardMapping(100, -1), //
	GetKeyboardMapping(101, -1), //
	ChangeKeyboardControl(102, -1), //
	GetKeyboardControl(103, -1), //
	Bell(104, -1), //
	SetPointerMapping(116, -1), //
	GetPointerMapping(117, -1), //
	ChangePointerControl(105, -1), //
	GetPointerControl(106, -1), //
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
