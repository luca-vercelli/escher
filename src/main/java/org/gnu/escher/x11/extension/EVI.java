package org.gnu.escher.x11.extension;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.RequestOutputStream;
import org.gnu.escher.x11.ResponseInputStream;
import org.gnu.escher.x11.VisualInfo;

/**
 * Extended Visual Information Extension. The specification can be found
 * <a href=
 * "http://escher.sourceforge.net/etc/specification/evi.ps.gz">here</a>.
 */
public class EVI extends Extension {
	private static final String[] MINOR_OPCODE_STRINGS = { "GetVersion", // 0
			"GetVisualInfo" // 1
	};

	public static final int CLIENT_MAJOR_VERSION = 1;
	public static final int CLIENT_MINOR_VERSION = 0;

	private int serverMajorVersion, serverMinorVersion;

	// evi opcode 0 - get version
	/**
	 * @see <a href="XeviQueryVersion.html">XeviQueryVersion</a>
	 */
	public EVI(Display display) throws ExtensionNotFoundException {
		super(display, "Extended-Visual-Information", MINOR_OPCODE_STRINGS);

		// check version before any other operations
		/*
		 * Note that the specification says the request includes a major and a minor
		 * version, but most implementation (xfree86 3.3/4.0) does not. Which one is
		 * bugged?
		 */
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 0, 1);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				serverMajorVersion = i.readInt16();
				serverMinorVersion = i.readInt16();
				i.skip(20);
			}
		}
	}

	/** EVI visual info. */
	public static class ExtendedVisualInfo {

		private int coreVisualID;
		private int screen;
		private int level;
		private int transparencyType;
		private int transparencyValue;
		private int minHWColormaps;
		private int maxHWColormaps;
		private int numColormapConflicts;

		ExtendedVisualInfo(ResponseInputStream i) {
			coreVisualID = i.readInt32();
			screen = i.readInt8();
			level = i.readInt8();
			transparencyType = i.readInt8();
			i.skip(1);
			transparencyValue = i.readInt32();
			minHWColormaps = i.readInt8();
			maxHWColormaps = i.readInt8();
			numColormapConflicts = i.readInt8();
		}

		public int getCoreVisualID() {
			return coreVisualID;
		}

		public void setCoreVisualID(int coreVisualID) {
			this.coreVisualID = coreVisualID;
		}

		public int getScreen() {
			return screen;
		}

		public void setScreen(int screen) {
			this.screen = screen;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}

		public int getTransparencyType() {
			return transparencyType;
		}

		public void setTransparencyType(int transparencyType) {
			this.transparencyType = transparencyType;
		}

		public int getTransparencyValue() {
			return transparencyValue;
		}

		public void setTransparencyValue(int transparencyValue) {
			this.transparencyValue = transparencyValue;
		}

		public int getMinHWColormaps() {
			return minHWColormaps;
		}

		public void setMinHWColormaps(int minHWColormaps) {
			this.minHWColormaps = minHWColormaps;
		}

		public int getMaxHWColormaps() {
			return maxHWColormaps;
		}

		public void setMaxHWColormaps(int maxHWColormaps) {
			this.maxHWColormaps = maxHWColormaps;
		}

		public int getNumColormapConflicts() {
			return numColormapConflicts;
		}

		public void setNumColormapConflicts(int numColormapConflicts) {
			this.numColormapConflicts = numColormapConflicts;
		}

	}

	public static class VisualInfoReply {

		private int nConflicts;
		private ExtendedVisualInfo[] items;

		VisualInfoReply(ResponseInputStream i) {
			int nInfo = i.readInt32();
			nConflicts = i.readInt32();
			i.skip(16);
			items = new ExtendedVisualInfo[nInfo];

			for (ExtendedVisualInfo evi : items)
				evi = new ExtendedVisualInfo(i);
		}

		public int getnConflicts() {
			return nConflicts;
		}

		public void setnConflicts(int nConflicts) {
			this.nConflicts = nConflicts;
		}

		public ExtendedVisualInfo[] getItems() {
			return items;
		}

		public void setItems(ExtendedVisualInfo[] items) {
			this.items = items;
		}
	}

	// evi opcode 1 - get visual info
	/**
	 * @see <a href="XeviGetVisualInfo.html">XeviGetVisualInfo</a>
	 */
	public VisualInfoReply visualInfo(VisualInfo[] visuals) {
		VisualInfoReply reply;
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(majorOpcode, 1, 2 + visuals.length);
			o.writeInt32(visuals.length);
			for (VisualInfo v : visuals)
				o.writeInt32(v.getId());

			ResponseInputStream in = display.getResponseInputStream();
			synchronized (in) {
				in.readReply(o);
				in.skip(8);
				reply = new VisualInfoReply(in);

			}
		}
		return reply;
	}

	public String moreString() {
		return "\n  client-version: " + CLIENT_MAJOR_VERSION + "." + CLIENT_MINOR_VERSION + "\n  server-version: "
				+ serverMajorVersion + "." + serverMinorVersion;
	}
}
