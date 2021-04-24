package org.gnu.escher.x11.enums;

public enum GrabMode {
	SYNCHRONOUS(0), ASYNCHRONOUS(1);

	private int code;

	GrabMode(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
}