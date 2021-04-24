package org.gnu.escher.x11.enums;

public enum GrabStatus {
	SUCCESS(0), ALREADY_GRABBED(1), INVALID_TIME(2), NOT_VIEWABLE(3), FROZEN(4);

	private int code;

	GrabStatus(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static GrabStatus getByCode(int code) {
		switch (code) {
		case 0:
			return SUCCESS;
		case 1:
			return ALREADY_GRABBED;
		case 2:
			return INVALID_TIME;
		case 3:
			return NOT_VIEWABLE;
		case 4:
			return FROZEN;
		default:
			return SUCCESS;
		}
	}
}