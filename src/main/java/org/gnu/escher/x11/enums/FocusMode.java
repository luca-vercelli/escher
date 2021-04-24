package org.gnu.escher.x11.enums;

public enum FocusMode {
	NORMAL(0), GRAB(1), UNGRAB(2), WHILE_GRABBED(3);

	private int code;

	FocusMode(int code) {
		this.code = code;
	}

	public static FocusMode getByCode(int code) {
		switch (code) {
		case 0:
			return NORMAL;
		case 1:
			return GRAB;
		case 2:
			return UNGRAB;
		case 3:
			return WHILE_GRABBED;
		default:
			return NORMAL;
		}
	}

	public int getCode() {
		return code;
	}

}