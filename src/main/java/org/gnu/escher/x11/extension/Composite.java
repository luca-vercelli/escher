
package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.resource.Pixmap;
import org.gnu.escher.x11.resource.Region;
import org.gnu.escher.x11.resource.Window;

/**
 * Composite Extension.
 * 
 * @see https://www.x.org/releases/X11R7.7/doc/compositeproto/compositeproto.txt
 *      https://github.com/freedesktop/libXcomposite/blob/master/src/Xcomposite.c
 *      https://opensource.apple.com/source/X11proto/X11proto-40/compositeproto/compositeproto-0.4/compositeproto.h
 */
public class Composite extends Extension {

	public Composite(Display display) throws ExtensionNotFoundException {
		super(display, "composite", MINOR_OPCODE_STRINGS);

		// check version before any other operations
		queryVersion();
	}

	private static final String[] MINOR_OPCODE_STRINGS = { //
			"QueryVersion", // 0
			"RedirectWindow", // 1
			"RedirectSubwindows", // 2
			"UnredirectWindow", // 3
			"UnredirectWindows", // 4
			"CreateRegionFromBorderClip", // 5
			"NameWindowPixmap", // 6
			"CompositeGetOverlayWindow", // 7
			"CompositeReleaseOverlayWindow", // 8
			"RedirectCoordinate", // 9
			"TransformCoordinate", // 10
	};

	public static final int CLIENT_MAJOR_VERSION = 1;
	public static final int CLIENT_MINOR_VERSION = 1;
	int serverMajorVersion, serverMinorVersion;

	public enum UpdateType {
		AUTOMAITC, MANUAL
	}

	public static class CompositeCoordinate {
		Window child;
		int x, y;
	}

	// minor opcode 0
	public void queryVersion() {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 0, 2);
			o.writeInt16(CLIENT_MAJOR_VERSION);
			o.writeInt16(CLIENT_MINOR_VERSION);
			o.send();

			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				serverMajorVersion = i.readInt16();
				serverMinorVersion = i.readInt16();
			}
		}
	}

	// minor opcode 1
	public void redirectWindow(Window window, UpdateType updateType) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 1, 2);
			o.writeInt16(window.getId());
			o.writeInt16(updateType.ordinal());
			o.send();
		}
	}

	// minor opcode 2
	public void redirectSubwindows(Window window, UpdateType updateType) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 2, 2);
			o.writeInt16(window.getId());
			o.writeInt16(updateType.ordinal());
			o.send();
		}
	}

	// minor opcode 3
	public void unredirectWindow(Window window) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 3, 1);
			o.writeInt16(window.getId());
			o.send();
		}
	}

	// minor opcode 4
	public void unredirectWindows(Window window) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 4, 1);
			o.writeInt16(window.getId());
			o.send();
		}
	}

	// minor opcode 5
	public Region createRegionFromBorderClip(Window window, Display display) {
		Region region = new Region(display);
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 5, 1);
			o.writeInt16(region.getId());
			o.writeInt16(window.getId());
			o.send();
		}
		return region;
	}

	// minor opcode 6
	public void nameWindowPixmap(Window window, Pixmap pixmap) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 6, 2);
			o.writeInt16(window.getId());
			o.writeInt16(pixmap.getId());
			o.send();
		}
	}

	// minor opcode 7
	public Window getOverlayWindow(Window window) {
		RequestOutputStream o = display.getResponseOutputStream();
		Window overlay = null;
		synchronized (o) {
			o.beginRequest(majorOpcode, 7, 1);
			o.writeInt16(window.getId());
			o.send();

			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				int overlayId = i.readInt16();
				overlay = new Window(display, overlayId);
			}
		}
		return overlay;
	}

	// minor opcode 8
	public void releaseOverlayWindows(Window window) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 8, 1);
			o.writeInt16(window.getId());
			o.send();
		}
	}

	// minor opcode 9
	public void redirectCoordinates(Window window, boolean redirect) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 9, 1);
			o.writeInt16(window.getId());
			o.writeInt8(redirect ? 1 : 0);
			o.send();
		}
	}

	// minor opcode 10
	public void transformCoordinates(Window window, int serialNumber, int x, int y, CompositeCoordinate[] coordinates) {
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 10, 3);
			o.writeInt16(window.getId());
			o.writeInt32(serialNumber);
			o.writeInt16(x);
			o.writeInt16(y);

			// FIXME how is this serialized exactly?
			o.writeInt16(coordinates.length);
			for (CompositeCoordinate c : coordinates) {
				o.writeInt16(c.child.getId());
				o.writeInt16(c.x);
				o.writeInt16(c.y);
			}
			o.send();
		}
	}
}
