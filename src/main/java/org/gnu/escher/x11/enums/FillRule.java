package org.gnu.escher.x11.enums;

public enum FillRule {
	EVEN_ODD(0), WINDING(1);

	private int code;

	FillRule(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}