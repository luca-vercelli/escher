package org.gnu.escher.x11.enums;

public enum FillStyle {
	TILED(0), OPAQUE_STIPPLED(1), STIPPLED(2);

	private int code;

	FillStyle(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}