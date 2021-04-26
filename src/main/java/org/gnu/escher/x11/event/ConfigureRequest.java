package org.gnu.escher.x11.event;

import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.core.Window;
import org.gnu.escher.x11.enums.StackMode;
import org.gnu.escher.x11.types.Rectangle;

/** X configure request event. */
public final class ConfigureRequest extends Event {

	private int parentWindowID;
	private int windowID;
	private int siblingID;

	private int x;
	private int y;
	private int width;
	private int height;

	private int borderWidth;
	private int bitMask;

	public ConfigureRequest(Display display, ResponseInputStream in) {
		super(display, in);
		parentWindowID = in.readInt32();
		windowID = in.readInt32();
		siblingID = in.readInt32();
		x = in.readInt16();
		y = in.readInt16();
		width = in.readInt16();
		height = in.readInt16();
		borderWidth = in.readInt16();
		bitMask = in.readInt16();
		in.skip(4);
	}

	public Window.Changes changes() {
		Window.Changes c = new Window.Changes();

		c.stackMode(stackMode());
		c.sibling_id(siblingID());
		c.setX(getX());
		c.setY(getY());
		c.setWidth(getWidth());
		c.setHeight(getHeight());
		c.borderWidth(getBorderWidth());

		// since above function calls will change bitmask,
		// read bitmask last
		c.setBitmask(getBitmask());
		return c;
	}

	public StackMode stackMode() {
		return StackMode.getByCode(getDetail());
	}

	public int siblingID() {
		return siblingID;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getBorderWidth() {
		return borderWidth;
	}

	public int getBitmask() {
		return bitMask;
	}

	public Rectangle rectangle() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	public int getParentWindowID() {
		return parentWindowID;
	}

	public int getWindowID() {
		return windowID;
	}
}
