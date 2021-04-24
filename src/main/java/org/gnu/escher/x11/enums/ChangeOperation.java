package org.gnu.escher.x11.enums;

public enum ChangeOperation {
	INSERT(0), DELETE(1);

	private int code;

	ChangeOperation(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static ChangeOperation getOperation(int code) {
		return code == 0 ? ChangeOperation.INSERT : ChangeOperation.DELETE;
	}
}