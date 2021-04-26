package org.gnu.escher.x11.enums;

public enum JoinStyle {
	MITER(0), JOIN_ROUND(1), BEVEL(2);

	private int code;

	JoinStyle(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}