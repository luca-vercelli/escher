package org.gnu.escher.x11.enums;

public enum StackMode {
	ABOVE(0), BELOW(1), TOP_IF(2), BOTTOM_IF(3), OPPOSITE(4);

	private int code;

	StackMode(int code) {
		this.code = code;
	}

	public int getCode() {

		return code;
	}

	public static StackMode getByCode(int code) {
		switch (code) {
		case 0:
			return ABOVE;
		case 1:
			return BELOW;
		case 2:
			return TOP_IF;
		case 3:
			return BOTTOM_IF;
		case 4:
			return OPPOSITE;
		default:
			return ABOVE;
		}
	}
}