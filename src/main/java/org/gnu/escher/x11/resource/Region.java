package org.gnu.escher.x11.resource;

import org.gnu.escher.x11.Display;

/** XSeverRegion */
public class Region extends Resource {
	/** Create. */
	public Region(Display display) {
		super(display);
	}

	/** Predefined. */
	public Region(int id) {
		super(id);
	}

	/** Intern. Create empty resource with given id */
	public Region(Display display, int id) {
		super(display, id);
	}

}
