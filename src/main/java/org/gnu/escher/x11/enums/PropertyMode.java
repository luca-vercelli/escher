package org.gnu.escher.x11.enums;

public enum PropertyMode {
	REPLACE(0), PREPEND(1), APPEND(2);

	private int code;

	PropertyMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}