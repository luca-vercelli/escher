package org.gnu.escher.x11.extension.render;

import org.gnu.escher.x11.Resource;
import org.gnu.escher.x11.core.Display;

/**
 * A simple picture. This is used in the render extension for solid colors.
 *
 * @see Render#create_solid_fill
 */
public class Picture extends Resource {
	/**
	 * A null picture.
	 *
	 * @see org.gnu.escher.x11.Window#NONE
	 */
	public static final Picture NONE = new Picture(0);

	private Picture(int id) {
		super(id);
	}

	/**
	 * Creates a new Picture object.
	 *
	 * @param display the display to connect to
	 */
	Picture(Display display) {
		super(display);
	}
}
