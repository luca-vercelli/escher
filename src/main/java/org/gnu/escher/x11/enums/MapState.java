package org.gnu.escher.x11.enums;

public enum MapState {
	UNMAPPED(0), UNVIEWABLE(1), VIEWABLE(2);

	private int code;

	private MapState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static MapState getByCode(int code) {
		switch (code) {
		case 0:
			return UNMAPPED;
		case 1:
			return UNVIEWABLE;
		case 2:
			return VIEWABLE;
		default:
			return UNMAPPED;
		}
	}
}