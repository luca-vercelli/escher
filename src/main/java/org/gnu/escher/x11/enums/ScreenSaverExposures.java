package org.gnu.escher.x11.enums;

public enum ScreenSaverExposures {
	NO(0), YES(1), DEFAULT(2);

	private int codeID;

	ScreenSaverExposures(int opID) {
		this.codeID = opID;
	}

	public int getCodeID() {
		return codeID;
	}

	public static ScreenSaverExposures getOption(boolean b) {
		return (b) ? ScreenSaverExposures.NO : ScreenSaverExposures.YES;
	}
}