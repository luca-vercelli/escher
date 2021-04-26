package org.gnu.escher.x11.enums;

public enum SubWindowMode {
	CLIP_BY_CHILDREN(0), INCLUDE_INTERIORS(1);

	private int code;

	SubWindowMode(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}
}