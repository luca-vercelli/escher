package org.gnu.escher.x11.enums;

public enum CirculateDirection {
	RAISE_LOWEST(0), LOWER_HIGHEST(1);

	private int code;

	CirculateDirection(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}