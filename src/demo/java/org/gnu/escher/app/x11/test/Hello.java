package org.gnu.escher.app.x11.test;

import org.gnu.escher.app.Application;
import org.gnu.escher.x11.WindowAttributes;
import org.gnu.escher.x11.core.Window;
import org.gnu.escher.x11.enums.EventMask;
import org.gnu.escher.x11.event.*;
import org.gnu.escher.x11.keysym.MiscKeySym;

/**
 * Hello World.
 *
 * <p>
 * This program covers the basic elements of a primitive X application. It
 * intentionally does not base on {@link Graphics}.
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Hello.gif">
 *      screenshot</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Hello.help"> help
 *      output</a>
 * 
 * @see Hello2
 */
public class Hello extends Application {
	public Hello(String[] args) {
		super(args);

		about("0.1", "hello world", "Stephen Tse <stephent@sfu.ca>", "http://escher.sourceforge.net/",
				"\nTo quit, press 'q', 'Q', ESCAPE, or any button.");

		if (help_option)
			return;

		WindowAttributes win_attr = new WindowAttributes();
		win_attr.setBackground(display.getDefaultWhite());
		win_attr.setBorder(display.getDefaultBlack());
		win_attr.setEventMask(
				EventMask.BUTTON_PRESS_MASK.or(EventMask.EXPOSURE_MASK.or(EventMask.KEY_PRESS_MASK)));
		Window window = new Window(display.getDefaultRoot(), 10, 10, 100, 50, 5, win_attr);

		window.setWM(this, "main");
		window.setWMDeleteWindow();
		window.map();
		display.flush();

		while (!exit_now) {
			Event event = display.nextEvent();

			switch (event.getCode()) {
			case BUTTON_PRESS:
				exit();
				break;

			case CLIENT_MESSAGE:
				if (((ClientMessage) event).deleteWindow())
					exit();
				break;

			case EXPOSE:
				if (((Expose) event).getCount() == 0) {
					window.text(display.getDefaultGC(), 20, 30, "Hello World!");
					display.flush();
				}
				break;

			case KEY_PRESS: {
				KeyPress e = (KeyPress) event;

				int keycode = e.detail();
				int keystate = e.getState();
				int keysym = display.getInput().keycodeToKeysym(keycode, keystate);

				if (keysym == 'q' || keysym == 'Q' || keysym == MiscKeySym.ESCAPE)
					exit();
				break;
			}
			default:
				break;
			}
		}

		display.close();
	}

	public static void main(String[] args) {
		new Hello(args);
	}
}
