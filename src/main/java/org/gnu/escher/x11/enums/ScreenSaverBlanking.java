package org.gnu.escher.x11.enums;

public enum ScreenSaverBlanking {
	NO(0), YES(1), DEFAULT(2);

	private int codeID;

	ScreenSaverBlanking(int opID) {
		this.codeID = opID;
	}

	public int getCodeID() {
		return codeID;
	}

	public static ScreenSaverBlanking getOption(boolean b) {
		return (b) ? ScreenSaverBlanking.NO : ScreenSaverBlanking.YES;
	}
}