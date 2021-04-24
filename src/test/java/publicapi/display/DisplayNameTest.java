package publicapi.display;

import java.io.*;
import java.net.*;
import mockit.*;

import org.gnu.escher.x11.*;
import org.gnu.escher.x11.DisplayName;
import org.junit.jupiter.api.*;
import org.newsclub.net.unix.*;

import static org.assertj.core.api.Assertions.*;
import static org.gnu.escher.x11.DisplayName.*;
import static org.junit.jupiter.api.Assertions.*;

public class DisplayNameTest {
	@Test
	void parse_null_fails() {
		NullPointerException exception = assertThrows(NullPointerException.class, () -> getFromConventionalString(null));
		assertThat(exception).hasMessage("convention is marked non-null but is null");
	}

	@Test
	void parse_empty_fails() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> getFromConventionalString(""));
		assertThat(exception).hasMessage("convention must not be blank.");
	}

	@Test
	void parse_negative_displayNumber_fails() {
		X11ClientException exception = assertThrows(X11ClientException.class, () -> getFromConventionalString(":-1"));
		assertThat(exception).hasMessage("expected displayNumber > 0 but was \"-1\".");
	}

	@Test
	void parse_negative_screenNumber_fails() {
		X11ClientException exception = assertThrows(X11ClientException.class, () -> getFromConventionalString(":1.-1"));
		assertThat(exception).hasMessage("expected screenNumber > 0 but was \"-1\".");
	}

	@Test
	void parse_hostName() {
		org.gnu.escher.x11.DisplayName name = getFromConventionalString("hostName");
		assertThat(name.getHostName()).isEqualTo("hostName");
		assertThat(name.getDisplayNumber()).isEqualTo(0);
		assertThat(name.getScreenNumber()).isEqualTo(0);
		assertThat(name.toString()).isEqualTo("hostName:0.0");
		assertThat(name.getSocketFile()).isNull();
	}

	@Test
	void parse_hostName_displayNumber() {
		org.gnu.escher.x11.DisplayName name = getFromConventionalString("hostName:2");
		assertThat(name.getHostName()).isEqualTo("hostName");
		assertThat(name.getDisplayNumber()).isEqualTo(2);
		assertThat(name.getScreenNumber()).isEqualTo(0);
		assertThat(name.toString()).isEqualTo("hostName:2.0");
		assertThat(name.getSocketFile()).isNull();
	}

	@Test
	void parse_hostName_displayNumber_screenNumber() {
		org.gnu.escher.x11.DisplayName name = getFromConventionalString("hostName:2.1");
		assertThat(name.getHostName()).isEqualTo("hostName");
		assertThat(name.getDisplayNumber()).isEqualTo(2);
		assertThat(name.getScreenNumber()).isEqualTo(1);
		assertThat(name.toString()).isEqualTo("hostName:2.1");
		assertThat(name.getSocketFile()).isNull();
	}

	@Test
	void parse_displayNumber() {
		org.gnu.escher.x11.DisplayName name = getFromConventionalString(":2");
		assertThat(name.getHostName()).isNull();
		assertThat(name.getDisplayNumber()).isEqualTo(2);
		assertThat(name.getScreenNumber()).isEqualTo(0);
		assertThat(name.toString()).isEqualTo(":2.0");
		assertThat(name.getSocketFile()).isEqualTo(new File("/tmp/.X11-unix/X2"));
	}

	@Test
	void parse_displayNumber_screenNumber() {
		DisplayName name = getFromConventionalString(":2.1");
		assertThat(name.getHostName()).isNull();
		assertThat(name.getDisplayNumber()).isEqualTo(2);
		assertThat(name.getScreenNumber()).isEqualTo(1);
		assertThat(name.toString()).isEqualTo(":2.1");
		assertThat(name.getSocketFile()).isEqualTo(new File("/tmp/.X11-unix/X2"));
	}

	@Test
	void connect_unix_exception_creating() throws IOException {
		IOException cause = new IOException("expected");
		new MockUp<AFUNIXSocketAddress>() {
			@Mock
			public void $init(File socketFile) throws IOException {
				throw cause;
			}
		};
		X11ClientException result = assertThrows(X11ClientException.class, () -> getFromConventionalString(":0").connect());
		assertThat(result).hasMessage("Failed to create connection to \":0.0\".");
		assertThat(result).hasCause(cause);
	}

	@Test
	void connect_unix_socket(@Mocked Display display, @Mocked AFUNIXSocket socket, @Mocked AFUNIXSocketAddress address)
			throws IOException {
		getFromConventionalString(":2.1").connect();
		new Verifications() {
			{
				AFUNIXSocket.connectTo((AFUNIXSocketAddress) any);
				new Display((Socket) any, 2, 1);
			}
		};
	}

	@Test
	void connect_tcp_socket_unknown_host(@Mocked InetAddress address) throws UnknownHostException {
		UnknownHostException cause = new UnknownHostException("expected");
		new Expectations() {
			{
				InetAddress.getByName("hostName");
				result = cause;
			}
		};
		X11ClientException exception = assertThrows(X11ClientException.class, () -> getFromConventionalString("hostName:2.1").connect());
		assertThat(exception).hasMessage("Failed to create connection to \"hostName:2.1\".");
		assertThat(exception).hasCause(cause);
	}

	@Test
	void connect_tcp_socket_hostname(@Mocked Display display, @Mocked Socket socket, @Mocked InetAddress address)
			throws IOException {
		getFromConventionalString("hostName:2.1").connect();
		new Verifications() {
			{
				InetAddress.getByName("hostName");
				new Display((Socket) any, 2, 1);
			}
		};
	}

	@Test
	void connect_tcp_socket_localhost(@Mocked Display display, @Mocked Socket socket, @Mocked InetAddress address)
			throws IOException {
		getFromConventionalString("hostName:2.1").withHostName(null).connect();
		new Verifications() {
			{
				InetAddress.getLocalHost();
				new Display((Socket) any, 2, 1);
			}
		};
	}
}
