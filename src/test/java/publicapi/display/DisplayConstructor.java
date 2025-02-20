package publicapi.display;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Optional;

import org.gnu.escher.x11.Display;
import org.gnu.escher.x11.X11ClientException;
import org.gnu.escher.x11.XAuthority;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;

public class DisplayConstructor {
	@Test
	void constructor_null_socket() {
		assertThrows(IllegalArgumentException.class, () -> new Display(null, 0, 0));
	}

	@Test
	void constructor_localhost_UnknownHost(@Injectable Socket socket) throws UnknownHostException {
		UnknownHostException cause = new UnknownHostException();
		new Expectations() {
			{
				socket.getInetAddress().getHostName();
				result = "localhost";
			}
		};
		new MockUp<InetAddress>() {
			@Mock
			InetAddress getLocalHost() throws UnknownHostException {
				throw cause;
			}
		};
		X11ClientException exception = assertThrows(X11ClientException.class, () -> new Display(socket, 0, 0));
		assertThat(exception).hasCause(cause);
	}

	@Test
	@Disabled
	void constructor(@Injectable Socket socket, @Injectable InetAddress inetAddress, @Injectable XAuthority xAuthority,
			@Injectable InputStream in, @Injectable OutputStream out) throws IOException {
		new Expectations() {
			{
				socket.getInetAddress().getHostName();
				result = "localhost";
				socket.getInputStream();
				result = in;
				socket.getOutputStream();
				result = out;
				inetAddress.getHostName();
				result = "n1";
				xAuthority.getProtocolName();
				result = "MIT-MAGIC-COOKIE-1";
				xAuthority.getProtocolData();
				result = new byte[] { 1, 2, 3 };
			}
		};
		new MockUp<InetAddress>() {
			@Mock
			InetAddress getLocalHost() {
				return inetAddress;
			}
		};
		new MockUp<XAuthority>() {
			@Mock
			Optional<XAuthority> getAuthority(String hostName) {
				return Optional.of(xAuthority);
			}
		};

		new Display(socket, 1, 2);
	}
}
