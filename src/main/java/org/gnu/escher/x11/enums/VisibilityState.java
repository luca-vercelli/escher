package org.gnu.escher.x11.enums;

public enum VisibilityState {
	UNOBSCURED(0), PARTIALLY_UNOBSCURED(1), FULLY_UNOBSCURED(2);

	private int code;

	VisibilityState(int code) {
		this.code = code;
	}

	public static VisibilityState getByCode(int code) {
		switch (code) {
		case 0:
			return UNOBSCURED;
		case 1:
			return PARTIALLY_UNOBSCURED;
		case 2:
			return FULLY_UNOBSCURED;
		default:
			return UNOBSCURED;
		}
	}

	public int getCode() {
		return code;
	}
}