package org.gnu.escher.x11.enums;

/**
 * The possible pixmap formats for client side images.
 */
public enum Format {

	BITMAP(0), XYPIXMAP(1), ZPIXMAP(2);

	private int id;

	Format(int id) {

		this.id = id;
	}

	public int getID() {
		return this.id;
	}
}