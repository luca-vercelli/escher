package org.gnu.escher.x11.enums;

public enum BackingStore {
	NEVER(0) {
		public String toString() {
			return "never";
		}
	},
	WHEN_MAPPED(1) {
		public String toString() {
			return "when-mapped";
		}
	},
	ALWAYS(2) {
		public String toString() {
			return "always";
		}
	};

	private int code;

	private BackingStore(int code) {
		this.code = code;
	}

	public abstract String toString();

	public static BackingStore getCode(int code) {
		switch (code) {
		case 0:
			return NEVER;
		case 1:
			return WHEN_MAPPED;
		case 2:
			return ALWAYS;
		default:
			return NEVER;
		}
	}

	public int getCode() {
		return this.code;
	}
}