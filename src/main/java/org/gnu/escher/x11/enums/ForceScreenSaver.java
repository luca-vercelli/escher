package org.gnu.escher.x11.enums;

public enum ForceScreenSaver {
	ACTIVATE(0), RESET(1);

	private int code;

	ForceScreenSaver(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static ForceScreenSaver getOperation(int code) {
		return code == 0 ? ForceScreenSaver.ACTIVATE : ForceScreenSaver.RESET;
	}
}