package org.gnu.escher.x11.enums;

public enum WinClass {
	COPY_FROM_PARENT(0), INPUT_OUTPUT(1), INPUT_ONLY(2);

	private int code;

	WinClass(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static WinClass getByID(int id) {
		switch (id) {
		case 0:
			return COPY_FROM_PARENT;
		case 1:
			return INPUT_OUTPUT;
		case 2:
			return INPUT_ONLY;
		default:
			return COPY_FROM_PARENT;
		}
	}
}