package org.gnu.escher.x11.enums;

public enum Shape {
	DESTROY(0), RETAIN_PERMANENT(1), RETAIN_TEMPORARY(2);

	private int code;

	Shape(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static Shape getFamily(int code) {
		switch (code) {
		case 0:
			return Shape.DESTROY;
		case 1:
			return Shape.RETAIN_PERMANENT;
		case 2:
			return Shape.RETAIN_TEMPORARY;
		default:
			return Shape.DESTROY;
		}
	}
}