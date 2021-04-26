package org.gnu.escher.app.x11.test;

import java.util.Random;

import org.gnu.escher.x11.event.*;

/**
 * Test multi-threading and synchronization.
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Sync.gif">
 *      screenshot</a>
 * 
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Sync.output"> text
 *      output</a>
 *
 * @see <a href="../../../../etc/screenshot/gnu/x11/test/Sync.help"> help
 *      output</a>
 */
public class Sync extends Graphics implements Runnable {
	public static final Random random = new Random();
	public Thread thread = new Thread(this, "paint");

	public Sync(String[] args) {
		super(args, 256, 256);

		about("0.1", "test multi-threading and synchronization", "Stephen Tse <stephent@sfu.ca>",
				"http://escher.sourceforge.net/");

		if (help_option)
			return;
		leave_display_open = true;

		System.out.println("stress test with send-mode = round-trip");
	}

	public void dispatch_event() {
		System.out.println("blocking-read event");
		event = display.nextEvent();
		System.out.println("got event " + event);

		switch (event.getCode()) {
		case BUTTON_PRESS:
			exit();
			break;

		case CLIENT_MESSAGE:
			if (((ClientMessage) event).deleteWindow())
				exit();
			break;

		case EXPOSE:
			if (!thread.isAlive())
				thread.start();
			break;

		case KEY_PRESS: {
			KeyPress e = (KeyPress) event;

			int keycode = e.detail();
			int keystate = e.getState();
			int keysym = display.getInput().keycodeToKeysym(keycode, keystate);

			if (keysym == 'q' || keysym == 'Q' || keysym == org.gnu.escher.x11.keysym.MiscKeySym.ESCAPE)
				exit();
			break;

		}
		}
	}

	protected void exit() {
		super.exit();
		thread.interrupt();
	}

	public void run() {
		while (!exit_now) {
			// generate round-trip request during `Display#next_event()'
			System.out.println("try round-trip request");
			display.getInput().inputFocus();
			System.out.println("done round-trip request");

			// generate one-way request during `Display#next_event()'
			window.line(display.getDefaultGC(), random.nextInt(window.width), random.nextInt(window.height),
					random.nextInt(window.width), random.nextInt(window.height));
			display.flush();

			if (!exit_now) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}

		display.close();
	}

	public static void main(String[] args) {
		new Sync(args).exec();
	}
}
