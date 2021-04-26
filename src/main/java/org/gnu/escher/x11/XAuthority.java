
package org.gnu.escher.x11;

import static org.gnu.escher.utils.Validation.requiresNonBlank;
import static org.gnu.escher.utils.Validation.requiresNonNegative;
import static org.gnu.escher.utils.Validation.requiresNonNull;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.gnu.escher.x11.core.X11ClientException;
import org.gnu.escher.x11.enums.XAuthorityFamily;

/**
 * An XAuthority.
 * https://gitlab.freedesktop.org/xorg/lib/libxau/-/blob/master/include/X11/Xauth.h
 * https://gitlab.freedesktop.org/xorg/lib/libxau
 */
public class XAuthority {

	XAuthorityFamily family;
	byte[] address;
	int displayNumber;
	String protocolName;
	byte[] protocolData;

	public XAuthority(XAuthorityFamily family, byte[] address, int displayNumber, String protocolName,
			byte[] protocolData) {
		requiresNonNull("family", family);
		requiresNonNull("address", address);
		requiresNonNull("protocolData", protocolData);
		requiresNonBlank("protocolName", protocolName);
		requiresNonNegative("displayNumber", displayNumber);

		this.family = family;
		this.address = address;
		this.displayNumber = displayNumber;
		this.protocolName = protocolName;
		this.protocolData = protocolData;
	}

	/**
	 * Fetches the current Xauthority entries from $HOME/.Xauthority or whatever is
	 * specified in the environment variable $XAUTHORITY.
	 *
	 * @return the current Xauthority entries
	 */
	public static List<XAuthority> getAuthorities() {
		return getAuthorities(getXAuthorityFile());
	}

	/**
	 * Search for authorities file name first in env, then in user home directory.
	 * 
	 * @return
	 */
	public static File getXAuthorityFile() {
		String authFilename = System.getenv("XAUTHORITY");
		if (authFilename == null || authFilename.equals("")) {
			authFilename = System.getProperty("user.home") + File.separatorChar + ".Xauthority";
		}
		return new File(authFilename);
	}

	/**
	 * Deserialize list of authorities from given file
	 * 
	 * @param file
	 * @return
	 */
	public static List<XAuthority> getAuthorities(File file) {
		List<XAuthority> authorities = new ArrayList<>();
		try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {
			Optional<XAuthority> read = read(in);
			while (read.isPresent()) {
				XAuthority current = read.get();
				authorities.add(current);
				read = read(in);
			}
		} catch (IOException ex) {
			throw new X11ClientException(ex);
		}
		return authorities;
	}

	/**
	 * Deserialize object from stream
	 * 
	 * @param in
	 * @return
	 */
	public static Optional<XAuthority> read(DataInput in) {
		try {
			XAuthorityFamily family = XAuthorityFamily.getByCode(in.readUnsignedShort());
			int dataLength = in.readUnsignedShort();
			byte[] address = readBytes(in, dataLength);
			int number = Integer.parseInt(in.readUTF());
			String name = in.readUTF();
			dataLength = in.readUnsignedShort();
			byte[] data = readBytes(in, dataLength);
			return Optional.of(new XAuthority(family, address, number, name, data));
		} catch (IOException ex) {
			return Optional.empty();
		}
	}

	/**
	 * Search inside authorities list either (1) a WILD authority, or (2) an
	 * authority with corresponding InetAddress
	 * 
	 * @param hostName
	 * @return
	 */
	public static Optional<XAuthority> getAuthority(String hostName) {
		List<XAuthority> auths = getAuthorities();
		for (int i = 0; i < auths.size(); i++) {
			XAuthority auth = auths.get(i);
			switch (auth.getFamily()) {
			case WILD:
				return Optional.of(auth);
			default:
				try {
					InetAddress authAddress = InetAddress
							.getByName(new String(auth.getAddress(), StandardCharsets.UTF_8));
					InetAddress hostNameAddress = InetAddress.getByName(hostName);
					if (authAddress.equals(hostNameAddress)) {
						return Optional.of(auth);
					}
				} catch (UnknownHostException e) {
					// do nothing
				}
				break;
			}
		}
		return Optional.empty();
	}

	private static byte[] readBytes(DataInput in, int length) throws IOException {
		byte[] bytes = new byte[length];
		in.readFully(bytes);
		return bytes;
	}

	public XAuthorityFamily getFamily() {
		return family;
	}

	public void setFamily(XAuthorityFamily family) {
		this.family = family;
	}

	public byte[] getAddress() {
		return address;
	}

	public void setAddress(byte[] address) {
		this.address = address;
	}

	public int getDisplayNumber() {
		return displayNumber;
	}

	public void setDisplayNumber(int displayNumber) {
		this.displayNumber = displayNumber;
	}

	public String getProtocolName() {
		return protocolName;
	}

	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}

	public byte[] getProtocolData() {
		return protocolData;
	}

	public void setProtocolData(byte[] protocolData) {
		this.protocolData = protocolData;
	}
}
