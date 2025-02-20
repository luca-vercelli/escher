package org.gnu.escher.app.x11.test;

import org.gnu.escher.app.Application;
import org.gnu.escher.x11.enums.CursorShape;
import org.gnu.escher.x11.keysym.MiscKeySym;
import org.gnu.escher.x11.resource.Cursor;

/**
 * Test internal workings of <code>gnu.x11</code>.
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Internal.output"> text
 *      output</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Internal.help"> help
 *      output</a>
 */
public class Internal extends Application {
	public Internal(String[] args) {
		super(args);

		about("0.1", "test internal workings of the library", "Stephen Tse <stephent@sfu.ca>",
				"http://escher.sourceforge.net/");

		if (help_option)
			return;

		// xc-misc, overflow it first
		display.setResourceIndex(0xfffffff0);
		new Cursor(display, CursorShape.X_CURSOR);
		System.out.println("xc-misc allocation test passed");

		// keyboard mapping
		int keycode = display.getInput().keysymToKeycode(MiscKeySym.DELETE);
		System.out.println("keycode for DELETE: " + keycode);
		int keysym = display.getInput().keycodeToKeysym(keycode, 0);
		System.out.println("keysym for " + keycode + ": " + keysym);
		if (keysym != MiscKeySym.DELETE)
			throw new Error();
		if (display.getInput().getKeysymsPerKeycode() != 2)
			System.out.println("WARNING: keysyms-per-keycode > 2: " + display.getInput().getKeysymsPerKeycode());
	}

	public static void main(String[] args) {
		new Internal(args);
	}
}
