package org.gnu.escher.app.puppet;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.Window;

/** Wrapper of {@link Window} for {@link Puppet}. */
public class Client extends Window {

	// system attributes
	public AttributesReply attributes;
	public WMClassHint class_hint;
	public String name;
	public WMSizeHints size_hints;

	// internal states
	public boolean early_unmapped, early_destroyed;
	public int register = -1; // invalid register index
	public int saved_width, saved_height;
	public int state;

	/** Intern. Create empty resource with given id */
	public Client(Display display, int id) {
		super(display, id);
	}

	public static Object intern(Window window) {
		return intern(window.getDisplay(), window.getId());
	}

	/** Get resource with given id if exists, or create it */
	public static Object intern(Display display, int id) {
		Object value = display.getResources().get(id);
		if (value instanceof Client)
			return value;
		return new Client(display, id);
	}

	public static final String[] STATE_STRINGS = { "unmanaged", "normal", "hidden", "no-focus", "early-unmapped",
			"early-destroyed" };

	public String toString() {
		String name0 = name == null ? "" : "\"" + name + "\" ";
		String hint0 = class_hint == null ? "(" : class_hint.toString() + " (";
		return "#Client " + name0 + hint0 + STATE_STRINGS[state] + ") " + super.toString();
	}
}
