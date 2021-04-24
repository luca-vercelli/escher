package org.gnu.escher.x11.enums;

public enum XAuthorityFamily {
	INTERNET(0), LOCAL(256), WILD(65535), KRB5PRINCIPAL(254), LOCALHOST(252);

	private int code;

	XAuthorityFamily(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static XAuthorityFamily getByCode(int code) {
		for (XAuthorityFamily family : XAuthorityFamily.values()) {
			if (family.code == code) {
				return family;
			}
		}
		throw new IllegalArgumentException("Unsupported code \"" + code + "\"");
	}
}