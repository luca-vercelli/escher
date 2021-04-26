package org.gnu.escher.x11.color;

import org.gnu.escher.x11.core.VisualInfo;

/**
 * Contains method to allow conversion between pixel formats to use between
 * application and the XServer.
 * 
 * @author Mario Torre neugens@aicas.com
 */
abstract public class ColorMapper {

	public static ColorMapper getInstace() {
		return new GenericColorMapper();
	}

	abstract public int convertToTargetPixel(int pixel, VisualInfo visual);

	abstract public int convertToNativePixel(int pixel, VisualInfo visual);
}
