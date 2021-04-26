package org.gnu.escher.x11.enums;

public enum LineStyle {
	SOLID(0), DOUBLEDASH(1), ONOFFDASH(2);

	private int code;

	LineStyle(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}