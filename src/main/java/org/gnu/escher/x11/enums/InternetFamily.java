package org.gnu.escher.x11.enums;

public enum InternetFamily {
	INTERNET(0), DECNET(1), CHAOS(2);

	private int code;

	InternetFamily(int cd) {
		this.code = cd;
	}

	public int getCode() {
		return code;
	}

	public static InternetFamily getFamily(int code) {
		switch (code) {
		case 0:
			return InternetFamily.INTERNET;
		case 1:
			return InternetFamily.DECNET;
		case 2:
			return InternetFamily.CHAOS;

		default:
			return InternetFamily.INTERNET;
		}
	}
}