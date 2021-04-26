package org.gnu.escher.x11.enums;

public enum Gravity {
	FORGET(0), //
	NORTH_WEST(1), //
	NORTH(2), //
	NORTH_EAST(3), //
	WEST(4), //
	CENTER(5), //
	EAST(6), //
	SOUTH_WEST(7), //
	SOUTH(8), //
	SOUTH_EAST(9), //
	STATIC(10);

	private int code;

	private Gravity(int code) {
		this.code = code;
	}

	public int getCode() {

		return code;
	}
}