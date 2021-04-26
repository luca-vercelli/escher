package org.gnu.escher.x11;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.RequestOutputStream;
import org.gnu.escher.x11.core.Window;
import org.gnu.escher.x11.enums.CursorShape;
import org.gnu.escher.x11.enums.X11CoreRequest;

/** X cursor. */
public class Cursor extends Resource {
	/**
	 * Predefined cursor.
	 *
	 * @see Window#NONE
	 */
	public static final Cursor NONE = new Cursor(0);
	public static final Cursor CURRENT = new Cursor(1);

	// cursor font
	public static Font FONT = null;

	/** Predefined. */
	public Cursor(int id) {
		super(id);
	}

	/**
	 * @see <a href="XCreateFontCursor.html">XCreateFontCursor</a>
	 */
	public Cursor(Display display, CursorShape shape) {
		super(display);

		// X predefined special font
		if (FONT == null)
			FONT = new Font(display, "cursor");

		/*
		 * From <X11/Cursor.c>: The cursor font contains the shape glyph followed by the
		 * mask glyph; so character position 0 contains a shape, 1 the mask for 0, 2 a
		 * shape, etc.
		 */

		// black and white
		create(FONT, FONT, shape.getShapeID(), shape.getShapeID() + 1, 0, 0, 0, 1, 1, 1);
	}

	/**
	 * @see <a href="XCreateGlyphCursor.html">XCreateGlyphCursor</a>
	 */
	public Cursor(Font src, Font mask, CursorShape source_char, CursorShape mask_char, int fg_r, int fg_g, int fg_b,
			int bg_r, int bg_g, int bg_b) {

		super(src.display);
		create(src, mask, source_char.getShapeID(), mask_char.getShapeID(), fg_r, fg_g, fg_b, bg_r, bg_g, bg_b);
	}

	// opcode 93 - create cursor
	// @Question The variables mask_char and source_char aren't used here, do we
	// need them?
	// @Question Or they are here to make 'compatible' with the X protocol?
	/**
	 * @param mask possible: {@link Pixmap#NONE}
	 * @see <a href="XCreatePixmapCursor.html">XCreatePixmapCursor</a>
	 */
	public Cursor(Pixmap src, Pixmap mask, CursorShape source_char, CursorShape mask_char, int fg_r, int fg_g, int fg_b,
			int bg_r, int bg_g, int bg_b, int x, int y) {
		super(src.display);

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.CreateCursor.getOpcode(), 0, 8);
			o.writeInt32(id);
			o.writeInt32(src.id);
			o.writeInt32(mask.id);
			o.writeInt16(fg_r);
			o.writeInt16(fg_g);
			o.writeInt16(fg_b);
			o.writeInt16(bg_r);
			o.writeInt16(bg_g);
			o.writeInt16(bg_b);
			o.writeInt16(x);
			o.writeInt16(y);
			o.send();
		}
	}

	// opcode 94 - create glyph cursor
	public void create(Font src, Font mask, int source_char, int mask_char, int fg_r, int fg_g, int fg_b, int bg_r,
			int bg_g, int bg_b) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.CreateGlyphCursor.getOpcode(), 0, 8);
			o.writeInt32(id);
			o.writeInt32(src.id);
			o.writeInt32(mask.id);
			o.writeInt16(source_char);
			o.writeInt16(mask_char);
			o.writeInt16(fg_r);
			o.writeInt16(fg_g);
			o.writeInt16(fg_b);
			o.writeInt16(bg_r);
			o.writeInt16(bg_g);
			o.writeInt16(bg_b);
			o.send();

		}
	}

	// opcode 95 - free cursor
	/**
	 * @see <a href="XFreeCursor.html">XFreeCursor</a>
	 */
	public void free() {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.FreeCursor.getOpcode(), 0, 2);
			o.writeInt32(id);
			o.send();
		}
	}

	// opcode 96 - recolor cursor
	/**
	 * @see <a href="XRecolorCursor.html">XRecolorCursor</a>
	 */
	public void recolor(RGB foreground, RGB background) {

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.RecolorCursor.getOpcode(), 0, 5);
			o.writeInt32(id);
			o.writeInt16(foreground.getRed());
			o.writeInt16(foreground.getGreen());
			o.writeInt16(foreground.getBlue());
			o.writeInt16(background.getRed());
			o.writeInt16(background.getRed());
			o.writeInt16(background.getBlue());
			o.send();
		}
	}
}