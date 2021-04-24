package org.gnu.escher.x11.enums;

public enum PropertyState {
	NEW_VALUE(0), DELETED(1);

	private int code;

	PropertyState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static PropertyState getByCode(int code) {
		return code == 0 ? NEW_VALUE : DELETED;
	}
}