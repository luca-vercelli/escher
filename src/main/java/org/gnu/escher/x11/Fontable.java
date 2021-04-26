package org.gnu.escher.x11;

import org.gnu.escher.x11.core.Atom;
import org.gnu.escher.x11.core.Display;
import org.gnu.escher.x11.core.RequestOutputStream;
import org.gnu.escher.x11.core.ResponseInputStream;
import org.gnu.escher.x11.enums.X11CoreRequest;

/** X fontable. */
public abstract class Fontable extends Resource {

	/** Predefined. */
	public Fontable(int id) {
		super(id);
	}

	/** Create. */
	public Fontable(Display display) {
		super(display);
	}

	/** Intern. */
	public Fontable(Display display, int id) {
		super(display, id);
	}

	/** Reply of {@link #info()}. */
	public class FontInfo implements InputStreamObject {

		private CharInfo min_bounds;
		private CharInfo max_bounds;
		private int minCharOrByte2;
		private int maxCharOrByte2;
		private int defaultChar;
		private int drawDirection;
		private int minByte1;
		private int maxByte1;
		private boolean allCharsExist;
		private int fontAscent;
		private int fontDescent;
		private FontProperty[] properties;
		private CharInfo[] charInfos;

		FontInfo(ResponseInputStream i) {
			min_bounds = new CharInfo(i);
			i.skip(4);
			max_bounds = new CharInfo(i);
			i.skip(4);
			minCharOrByte2 = i.readInt16();
			maxCharOrByte2 = i.readInt16();
			defaultChar = i.readInt16();
			int num_props = i.readInt16();
			drawDirection = i.readInt8();
			minByte1 = i.readInt8();
			maxByte1 = i.readInt8();
			allCharsExist = i.readBool();
			fontAscent = i.readInt16();
			fontDescent = i.readInt16();
			int num_charinfo = i.readInt32();
			properties = new FontProperty[num_props];
			for (int j = 0; j < num_props; j++)
				properties[j] = new FontProperty(i);
			charInfos = new CharInfo[num_charinfo];
			for (int j = 0; j < num_charinfo; j++)
				charInfos[j] = new CharInfo(i);
		}

		/**
		 * Encapsulate an additional font property.
		 */
		public class FontProperty implements InputStreamObject {

			private int name_id;
			private int value;

			/**
			 * Creates a new FontProperty that starts at the specified index.
			 *
			 * @param i the starting index of the font property
			 */
			private FontProperty(ResponseInputStream i) {
				name_id = i.readInt32();
				value = i.readInt32();
			}

			public Atom name() {
				return (Atom) Atom.intern(display, name_id);
			}

			public int getName_id() {
				return name_id;
			}

			public void setName_id(int name_id) {
				this.name_id = name_id;
			}

			public int getValue() {
				return value;
			}

			public void setValue(int value) {
				this.value = value;
			}
		}

		/**
		 * Encapsulates information about one character.
		 */
		public class CharInfo implements InputStreamObject {

			private int leftSideBearing;
			private int rightSideBearing;
			private int characterWidth;
			private int ascent;
			private int descent;
			private int attributes;

			/**
			 * Creates a new CharInfo instance that starts at the specified offset in the
			 * response.
			 *
			 * @param i the starting index of the CharInfo field
			 */
			private CharInfo(ResponseInputStream i) {
				leftSideBearing = i.readInt16();
				rightSideBearing = i.readInt16();
				characterWidth = i.readInt16();
				ascent = i.readInt16();
				descent = i.readInt16();
				attributes = i.readInt16();
			}

			public int character_width() {
				return characterWidth;
			}

			public int getLeftSideBearing() {
				return leftSideBearing;
			}

			public void setLeftSideBearing(int leftSideBearing) {
				this.leftSideBearing = leftSideBearing;
			}

			public int getRightSideBearing() {
				return rightSideBearing;
			}

			public void setRightSideBearing(int rightSideBearing) {
				this.rightSideBearing = rightSideBearing;
			}

			public int getCharacterWidth() {
				return characterWidth;
			}

			public void setCharacterWidth(int characterWidth) {
				this.characterWidth = characterWidth;
			}

			public int getAscent() {
				return ascent;
			}

			public void setAscent(int ascent) {
				this.ascent = ascent;
			}

			public int getDescent() {
				return descent;
			}

			public void setDescent(int descent) {
				this.descent = descent;
			}

			public int getAttributes() {
				return attributes;
			}

			public void setAttributes(int attributes) {
				this.attributes = attributes;
			}
		}

		public static final int LEFT_TO_RIGHT = 0;
		public static final int RIGHT_TO_LEFT = 1;

		public int font_ascent() {
			return fontAscent;
		}

		public int font_descent() {
			return fontDescent;
		}

		public CharInfo max_bounds() {
			return max_bounds;
		}

		public CharInfo min_bounds() {
			return min_bounds;
		}

		public int max_byte1() {
			return maxByte1;
		}

		public int min_byte1() {
			return minByte1;
		}

		public int min_char_or_byte2() {
			return minCharOrByte2;
		}

		public int max_char_or_byte2() {
			return maxCharOrByte2;
		}

		public CharInfo[] char_infos() {
			return charInfos;
		}

		public CharInfo getMin_bounds() {
			return min_bounds;
		}

		public void setMin_bounds(CharInfo min_bounds) {
			this.min_bounds = min_bounds;
		}

		public CharInfo getMax_bounds() {
			return max_bounds;
		}

		public void setMax_bounds(CharInfo max_bounds) {
			this.max_bounds = max_bounds;
		}

		public int getMinCharOrByte2() {
			return minCharOrByte2;
		}

		public void setMinCharOrByte2(int minCharOrByte2) {
			this.minCharOrByte2 = minCharOrByte2;
		}

		public int getMaxCharOrByte2() {
			return maxCharOrByte2;
		}

		public void setMaxCharOrByte2(int maxCharOrByte2) {
			this.maxCharOrByte2 = maxCharOrByte2;
		}

		public int getDefaultChar() {
			return defaultChar;
		}

		public void setDefaultChar(int defaultChar) {
			this.defaultChar = defaultChar;
		}

		public int getDrawDirection() {
			return drawDirection;
		}

		public void setDrawDirection(int drawDirection) {
			this.drawDirection = drawDirection;
		}

		public int getMinByte1() {
			return minByte1;
		}

		public void setMinByte1(int minByte1) {
			this.minByte1 = minByte1;
		}

		public int getMaxByte1() {
			return maxByte1;
		}

		public void setMaxByte1(int maxByte1) {
			this.maxByte1 = maxByte1;
		}

		public boolean isAllCharsExist() {
			return allCharsExist;
		}

		public void setAllCharsExist(boolean allCharsExist) {
			this.allCharsExist = allCharsExist;
		}

		public int getFontAscent() {
			return fontAscent;
		}

		public void setFontAscent(int fontAscent) {
			this.fontAscent = fontAscent;
		}

		public int getFontDescent() {
			return fontDescent;
		}

		public void setFontDescent(int fontDescent) {
			this.fontDescent = fontDescent;
		}

		public FontProperty[] getProperties() {
			return properties;
		}

		public void setProperties(FontProperty[] properties) {
			this.properties = properties;
		}

		public CharInfo[] getCharInfos() {
			return charInfos;
		}

		public void setCharInfos(CharInfo[] charInfos) {
			this.charInfos = charInfos;
		}
	}

	// opcode 47 - query font
	/**
	 * @see <a href="XQueryFont.html">XQueryFont</a>
	 */
	public FontInfo info() {

		FontInfo info;
		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.QueryFont.getOpcode(), 0, 2);
			o.writeInt32(id);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(8);
				info = new FontInfo(i);
			}
		}

		return info;
	}

	/** Reply of {@link #textExtent(String)}. */
	public class TextExtentInfo implements InputStreamObject {

		private boolean leftToRight;
		private int fontAscent;
		private int fontDescent;
		private int overallAscent;
		private int overallDescent;
		private int overallWidth;
		private int overallLeft;
		private int overallRight;

		TextExtentInfo(ResponseInputStream i) {
			leftToRight = i.readBool();
			i.skip(6);
			fontAscent = i.readInt16();
			fontDescent = i.readInt16();
			overallAscent = i.readInt16();
			overallDescent = i.readInt16();
			overallWidth = i.readInt32();
			overallLeft = i.readInt32();
			overallRight = i.readInt32();
		}

		public int overallWidth() {
			return overallWidth;
		}

		public boolean isLeftToRight() {
			return leftToRight;
		}

		public void setLeftToRight(boolean leftToRight) {
			this.leftToRight = leftToRight;
		}

		public int getFontAscent() {
			return fontAscent;
		}

		public void setFontAscent(int fontAscent) {
			this.fontAscent = fontAscent;
		}

		public int getFontDescent() {
			return fontDescent;
		}

		public void setFontDescent(int fontDescent) {
			this.fontDescent = fontDescent;
		}

		public int getOverallAscent() {
			return overallAscent;
		}

		public void setOverallAscent(int overallAscent) {
			this.overallAscent = overallAscent;
		}

		public int getOverallDescent() {
			return overallDescent;
		}

		public void setOverallDescent(int overallDescent) {
			this.overallDescent = overallDescent;
		}

		public int getOverallWidth() {
			return overallWidth;
		}

		public void setOverallWidth(int overallWidth) {
			this.overallWidth = overallWidth;
		}

		public int getOverallLeft() {
			return overallLeft;
		}

		public void setOverallLeft(int overallLeft) {
			this.overallLeft = overallLeft;
		}

		public int getOverallRight() {
			return overallRight;
		}

		public void setOverallRight(int overallRight) {
			this.overallRight = overallRight;
		}
	}

	// opcode 48 - query text extents
	/**
	 * @see <a href="XQueryTextExtents.html">XQueryTextExtents</a>
	 */
	public TextExtentInfo textExtent(String s) {

		TextExtentInfo info;
		boolean odd = s.length() % 2 == 1;
		int pad = odd ? 2 : 0;
		int len = 2 + (2 * s.length() + pad) / 4;

		RequestOutputStream o = display.getResponseOutputStream();
		synchronized (o) {
			o.beginRequest(X11CoreRequest.QueryTextExtents.getOpcode(), odd ? 1 : 0, len);
			o.writeInt32(id);
			o.writeString16(s);
			ResponseInputStream i = display.getResponseInputStream();
			synchronized (i) {
				i.readReply(o);
				i.skip(1);
				info = new TextExtentInfo(i);
				i.skip(4);
			}
		}
		return info;
	}
}
