package org.gnu.escher.x11;

import org.gnu.escher.x11.enums.ArcMode;
import org.gnu.escher.x11.enums.CapStyle;
import org.gnu.escher.x11.enums.FillRule;
import org.gnu.escher.x11.enums.FillStyle;
import org.gnu.escher.x11.enums.FunctionValues;
import org.gnu.escher.x11.enums.JoinStyle;
import org.gnu.escher.x11.enums.LineStyle;
import org.gnu.escher.x11.enums.SubWindowMode;
import org.gnu.escher.x11.resource.Font;
import org.gnu.escher.x11.resource.Pixmap;
import org.gnu.escher.x11.types.ValueList;

/** X GC values. */
public class GCValues extends ValueList {
	public static final GCValues EMPTY = new GCValues();

	public GCValues() {
		super(23);
	}

	public void setFunction(FunctionValues fv) {
		set(0, fv.getCode());
	}

	/**
	 * @param i
	 *            default: all ones
	 */
	public void setPlaneMask(int i) {
		set(1, i);
	}

	/**
	 * @see #setForeground(int)
	 */
	public void setForeground(Color c) {
		setForeground(c.getPixel());
	}

	public void setForeground(int pixel) {
		set(2, pixel);
	}

	/**
	 * @see #setBackground(int)
	 */
	public void setBackground(Color c) {
		setBackground(c.getPixel());
	}

	public void setBackground(int pixel) {
		set(3, pixel);
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setLineWidth(int i) {
		set(4, i);
	}

	public void setLineStyle(LineStyle ls) {
		set(5, ls.getCode());
	}

	public void setCapStyle(CapStyle cp) {
		set(6, cp.getCode());
	}

	public void setJoinStyle(JoinStyle js) {
		set(7, js.getCode());
	}

	public void setFillStyle(FillStyle fi) {
		set(8, fi.getCode());
	}

	public void setFillRule(FillRule fr) {
		set(9, fr.getCode());
	}

	/**
	 * @param p
	 *            default: pixmap of unspecified size filled with foreground pixel
	 */
	public void setTile(Pixmap p) {
		set(10, p.getId());
	}

	/**
	 * @param p
	 *            default: pixmap of unspecified size filled with ones
	 */
	public void setStipple(Pixmap p) {
		set(11, p.getId());
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setTileStippleXOrigin(int i) {
		set(12, i);
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setTileStippleYOrigin(int i) {
		set(13, i);
	}

	public void set_font(Font f) {
		set(14, f.getId());
	}

	public void setSubwindowMode(SubWindowMode swm) {
		set(15, swm.getCode());
	}

	/**
	 * @param b
	 *            default: true
	 */
	public void setGraphicsExposures(boolean b) {
		set(16, b);
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setClipXOrigin(int i) {
		set(17, i);
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setClipYOrigin(int i) {
		set(18, i);
	}

	/**
	 * @param p
	 *            possible: {@link Pixmap#NONE} (default)
	 */
	public void setClipMask(Pixmap p) {
		set(19, p.getId());
	}

	/**
	 * @param i
	 *            default: 0
	 */
	public void setDashOffset(int i) {
		set(20, i);
	}

	/**
	 * @param i
	 *            default: 4 (that is, the list [4, 4])
	 */
	public void setDashes(int i) {
		set(21, i);
	}

	public void setArcMode(ArcMode am) {
		set(22, am.getCode());
	}

	public Object clone() {
		GCValues values = new GCValues();
		values.copy(this);
		return values;
	}
}