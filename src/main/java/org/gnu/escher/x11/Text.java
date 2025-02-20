package org.gnu.escher.x11;

import org.gnu.escher.x11.resource.Font;

/** Text item for drawing text to X server. */
public class Text {
	private String str;
	private int delta;
	private Font font;

	public Text(String s) {
		this.str = s;
	}

	public Text(String s, int delta) {
		this.str = s;
		this.delta = delta;
	}

	public Text(String s, int delta, Font font) {
		this.str = s;
		this.delta = delta;
		this.font = font;
	}

	/**
	 * Lenght (in bytes), to be used in a X11 request, considering str, delta, font.
	 * If str is 16-bit, this is twice its length
	 * 
	 * @param bit
	 *            bits per character, either 8 or 16
	 * @return
	 */
	public int length(int bit) {

		// 2 = length of string field and delta field
		// 5 = font field
		int n = font == null ? 2 : 5;

		if (bit == 8 || str.charAt(0) > 128) // non-ascii
			n = n + str.length();
		else
			n = n + 2 * str.length();

		return n;
	}

	public String toString() {
		return "#Text [" + str + " " + delta + " " + font + "]";
	}

	public int getDelta() {
		return delta;
	}

	public Font getFont() {
		return font;
	}

	public String getStr() {
		return str;
	}
}
