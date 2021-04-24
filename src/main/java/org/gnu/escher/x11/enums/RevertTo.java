package org.gnu.escher.x11.enums;

public enum RevertTo {
	TO_NONE(0), TO_POINTER_ROOT(1), TO_PARENT(2);

	private int code;

	RevertTo(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}