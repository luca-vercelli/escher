package org.gnu.escher.x11.enums;

public enum KeyMask {
	// KEYBUTMASK - keyboard button mask
	SHIFT_MASK(1 << 0), LOCK_MASK(1 << 1), // cap lock
	CONTROL_MASK(1 << 2), MOD1_MASK(1 << 3), // alt key
	MOD2_MASK(1 << 4), // num lock
	MOD3_MASK(1 << 5), // menu key
	MOD4_MASK(1 << 6), // window key
	MOD5_MASK(1 << 7), // scroll lock
	BUTTON1_MASK(1 << 8), BUTTON2_MASK(1 << 9), BUTTON3_MASK(1 << 10), BUTTON4_MASK(1 << 11), BUTTON5_MASK(1 << 12),

	// 104 PC keyboard
	META_MASK(1 << 3), ALT_MASK(1 << 5), SUPER_MASK(1 << 6),

	// Mouse
	BUTTON1(1), BUTTON2(2), BUTTON3(3), BUTTON4(4), BUTTON5(5);

	private int code;

	KeyMask(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public boolean is(int code) {
		return this.code == code;
	}

	public int logicOr(KeyMask km) {
		return this.getCode() | km.getCode();
	}

	public int logicOr(int i) {
		return this.getCode() | i;
	}

	public static KeyMask getButton(int buttonID) {
		switch (buttonID) {
		case 1:
			return BUTTON1;
		case 2:
			return BUTTON2;
		case 3:
			return BUTTON3;
		default:
			return BUTTON1;
		}
	}
}