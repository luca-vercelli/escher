package org.gnu.escher.x11.enums;

public enum InputStatus {
	SUCCESS(0), BUSY(1), FAILED(2);

	private int code;

	InputStatus(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static InputStatus getStatus(int code) {
		switch (code) {
		case 0:
			return InputStatus.SUCCESS;
		case 1:
			return InputStatus.BUSY;
		case 2:
			return InputStatus.FAILED;

		default:
			return InputStatus.SUCCESS;
		}
	}
}