package org.gnu.escher.x11.enums;

public enum RectangleOrder {
	UN_SORTED(0), Y_SORTED(1), YX_SORTED(2), YX_BANDED(3);

	private int code;

	RectangleOrder(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}