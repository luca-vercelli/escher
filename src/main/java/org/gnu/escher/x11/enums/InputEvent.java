package org.gnu.escher.x11.enums;

public enum InputEvent {
	ASYNC_POINTER(0), SYNC_POINTER(1), REPLY_POINTER(2), ASYNC_KEYBOARD(3), SYNC_KEYBOARD(4), REPLY_KEYBOARD(5),
	ASYNC_BOTH(6), SYNC_BOTH(7);

	private int code;

	InputEvent(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

}