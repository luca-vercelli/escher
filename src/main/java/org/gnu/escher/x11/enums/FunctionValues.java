package org.gnu.escher.x11.enums;

public enum FunctionValues {
	CLEAR(0), //
	AND(1), //
	AND_REVERSE(2), //
	COPY(3), //
	AND_INVERTED(4), //
	NOOP(5), //
	XOR(6), //
	OR(7), //
	NOR(8), //
	EQUIV(9), //
	INVERT(10), //
	OR_REVERSE(11), //
	COPY_INVERTED(12), //
	OR_INVERTED(13), //
	NAND(14), //
	SET(15);

	private int code;

	FunctionValues(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}