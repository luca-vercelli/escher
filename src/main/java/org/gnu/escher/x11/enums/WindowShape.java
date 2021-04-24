package org.gnu.escher.x11.enums;

public enum WindowShape {
	DESTROY(0), RETAIN_PERMANENT(1), RETAIN_TEMPORARY(2);

	private int code;

	WindowShape(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static WindowShape getFamily(int code) {
		switch (code) {
		case 0:
			return WindowShape.DESTROY;
		case 1:
			return WindowShape.RETAIN_PERMANENT;
		case 2:
			return WindowShape.RETAIN_TEMPORARY;
		default:
			return WindowShape.DESTROY;
		}
	}
}