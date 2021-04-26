package org.gnu.escher.app.x11.test;

import org.gnu.escher.x11.core.GC;
import org.gnu.escher.x11.extension.ExtensionNotFoundException;
import org.gnu.escher.x11.extension.Shape;
import org.gnu.escher.x11.image.XBM;
import org.gnu.escher.x11.resource.Pixmap;

/**
 * Test Nonrectangular Window Extension.
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Shape.gif">
 *      screenshot</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Shape.output"> text
 *      output</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Shape.help"> help
 *      output</a>
 */
public class ShapeApp extends Graphics {
	public static final int[] xbm_data = { //
			0xff, 0x00, 0x00, 0xc0, 0xfe, 0x01, 0x00, 0xc0, //
			0xfc, 0x03, 0x00, 0x60, 0xf8, 0x07, 0x00, 0x30, //
			0xf8, 0x07, 0x00, 0x18, 0xf0, 0x0f, 0x00, 0x0c, //
			0xe0, 0x1f, 0x00, 0x06, 0xc0, 0x3f, 0x00, 0x06, //
			0xc0, 0x3f, 0x00, 0x03, 0x80, 0x7f, 0x80, 0x01, //
			0x00, 0xff, 0xc0, 0x00, 0x00, 0xfe, 0x61, 0x00, //
			0x00, 0xfe, 0x31, 0x00, 0x00, 0xfc, 0x33, 0x00, //
			0x00, 0xf8, 0x1b, 0x00, 0x00, 0xf0, 0x0d, 0x00, //
			0x00, 0xf0, 0x0e, 0x00, 0x00, 0x60, 0x1f, 0x00, //
			0x00, 0xb0, 0x3f, 0x00, 0x00, 0x98, 0x7f, 0x00, //
			0x00, 0x98, 0x7f, 0x00, 0x00, 0x0c, 0xff, 0x00, //
			0x00, 0x06, 0xfe, 0x01, 0x00, 0x03, 0xfc, 0x03, //
			0x80, 0x01, 0xfc, 0x03, 0xc0, 0x00, 0xf8, 0x07, //
			0xc0, 0x00, 0xf0, 0x0f, 0x60, 0x00, 0xe0, 0x1f, //
			0x30, 0x00, 0xe0, 0x1f, 0x18, 0x00, 0xc0, 0x3f, //
			0x0c, 0x00, 0x80, 0x7f, 0x06, 0x00, 0x00, 0xff //
	};

	public ShapeApp(String[] args) throws ExtensionNotFoundException {
		super(args, 32, 32);

		about("0.1", "test nonrectangular window extension", "Stephen Tse <stephent@sfu.ca>",
				"http://escher.sourceforge.net/");

		if (help_option)
			return;

		Pixmap mask = new Pixmap(window, 32, 32, 1);
		XBM xbm = new XBM(display, 32, 32, xbm_data);

		GC.Values gv = new GC.Values();
		gv.setBackground(display.getDefaultWhite());
		gv.setForeground(display.getDefaultBlack());
		GC gc = new GC(mask, gv);

		mask.put_image(gc, xbm, 0, 0);

		Shape shape = new Shape(display);

		// force a round trip after an error is generated
		display.getInput().inputFocus();

		// test extension event mechanism
		shape.selectInput(window, true);

		shape.combineMask(window, Shape.Kind.BOUNDING, 0, 0, mask, Shape.Operation.SUBTRACT);
	}

	public void dispatch_event() {
		super.dispatch_event();
		if (event instanceof Shape.NotifyEvent) {
			Shape.NotifyEvent ne = (Shape.NotifyEvent) event;
			System.out.println("shape notify: " + ne.rectangle());
		}
	}

	public static void main(String[] args) throws Exception {
		new ShapeApp(args).exec();
	}
}
