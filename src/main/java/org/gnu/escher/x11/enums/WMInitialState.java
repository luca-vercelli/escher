package org.gnu.escher.x11.enums;

public enum WMInitialState {
	WITHDRAWN(0), NORMAL(1), ICONIC(3);

	private int code;

	WMInitialState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static WMInitialState getByCode(int code) {
		switch (code) {
		case 0:
			return WITHDRAWN;
		case 1:
			return NORMAL;
		case 2:
			return ICONIC;
		default:
			return WITHDRAWN;
		}
	}
}