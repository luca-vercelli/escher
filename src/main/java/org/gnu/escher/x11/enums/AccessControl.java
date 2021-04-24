package org.gnu.escher.x11.enums;

public enum AccessControl {
	ENABLE(0), DISABLED(1);

	private int code;

	AccessControl(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static AccessControl getControl(boolean code) {
		return code ? AccessControl.ENABLE : AccessControl.DISABLED;
	}
}