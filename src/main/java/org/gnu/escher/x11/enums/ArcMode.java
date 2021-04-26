package org.gnu.escher.x11.enums;

public enum ArcMode {
	CHORD(0), PIE_SLICE(1);

	private int code;

	ArcMode(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}