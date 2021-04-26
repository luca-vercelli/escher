package org.gnu.escher.x11.core;

import org.gnu.escher.x11.InputStreamObject;
import org.gnu.escher.x11.enums.InternetFamily;

public class Host implements InputStreamObject {

	private InternetFamily family;
	private byte[] address;

	/**
	 * Reads one Host instance from a ResponseInputStream.
	 * 
	 * @param in the input stream to read from
	 */
	Host(ResponseInputStream in) {
		family = InternetFamily.getFamily(in.readInt8());
		in.skip(1);
		int addLen = in.readInt16();
		address = new byte[addLen];
		in.readData(address);
		in.pad(addLen);
	}

	public InternetFamily getFamily() {
		return family;
	}

	public void setFamily(InternetFamily family) {
		this.family = family;
	}

	public byte[] getAddress() {
		return address;
	}

	public void setAddress(byte[] address) {
		this.address = address;
	}
}
