package org.gnu.escher.x11.enums;

public enum CapStyle {
	NOT_LAST(0), BUTT(1), CAP_ROUND(2), PROJECTING(3);

	private int code;

	CapStyle(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}