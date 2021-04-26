package org.gnu.escher.x11;

import org.gnu.escher.x11.enums.BackingStore;
import org.gnu.escher.x11.enums.Gravity;
import org.gnu.escher.x11.resource.Colormap;
import org.gnu.escher.x11.resource.Cursor;
import org.gnu.escher.x11.resource.Pixmap;
import org.gnu.escher.x11.types.ValueList;

/** X window attributes. */
public class WindowAttributes extends ValueList {

	public WindowAttributes(int count) {
		super(count);
	}

	public final static WindowAttributes EMPTY = new WindowAttributes();

	public WindowAttributes() {

		super(15);
	}

	/**
	 * @param p
	 *            possible: {@link Pixmap#NONE} (default),
	 *            {@link Pixmap#PARENT_RELATIVE}
	 */
	public void setBackground(Pixmap p) {

		set(0, p.getId());
	}

	/**
	 * @see #setBackground(int)
	 */
	public void setBackground(Color c) {

		setBackground(c.getPixel());
	}

	public void setBackground(int pixel) {

		set(1, pixel);
	}

	/**
	 * @param p
	 *            possible: {@link Pixmap#COPY_FROM_PARENT} (default)
	 */
	public void setBorder(Pixmap p) {

		set(2, p.getId());
	}

	/**
	 * @see #setBorder(int)
	 */
	public void setBorder(Color c) {

		setBorder(c.getPixel());
	}

	public void setBorder(int pixel) {

		set(3, pixel);
	}

	/**
	 * @param gravity
	 */
	public void setWinGravity(Gravity gravity) {

		set(5, gravity.getCode());
	}

	public void setBackingStore(BackingStore bs) {

		set(6, bs.getCode());
	}

	/**
	 * @param i
	 *            default: all ones
	 */
	public void setBackingPlane(int i) {

		set(7, i);
	}

	/**
	 * #set_backing(int)
	 */
	public void setBacking(Color c) {

		setBacking(c.getPixel());
	}

	public void setBacking(int pixel) {

		set(8, pixel);
	}

	/**
	 * @param b
	 *            default: false
	 */
	public void setOverrideRedirect(boolean b) {

		set(9, b);
	}

	/**
	 * @param b
	 *            default: false
	 */
	public void setSaveUnder(boolean b) {

		set(10, b);
	}

	/**
	 * @param i
	 *            default: {}
	 */
	public void setEventMask(int i) { // todo should use Event.EventMask

		set(11, i);
	}

	public void addEventMask(int i) {

		setEventMask(eventMask() | i);
	}

	public int eventMask() {

		return data[11];
	}

	/**
	 * @param i
	 *            default: {}
	 */
	public void setDoNotPropagateMask(int i) {

		set(12, i);
	}

	/**
	 * @param c
	 *            possible: {@link Colormap#COPY_FROM_PARENT} (default)
	 */
	public void setColormap(Colormap c) {

		set(13, c.getId());
	}

	/**
	 * @param c
	 *            possible: {@link Cursor#NONE}
	 */
	public void setCursor(Cursor c) {

		set(14, c.getId());
	}

	public Object clone() {

		WindowAttributes attr = new WindowAttributes();
		attr.copy(this);
		return attr;
	}
}
