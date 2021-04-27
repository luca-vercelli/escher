package publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.gnu.escher.x11.X11ClientException;
import org.junit.jupiter.api.Test;

public class X11ClientExceptionTest {

	@Test
	void constructor_Throwable() {
		IOException cause = new IOException();
		X11ClientException exception = new X11ClientException(cause);
		assertThat(exception).hasCause(cause);
	}

	@Test
	void constructor_2() {
		IOException cause = new IOException();
		X11ClientException result = new X11ClientException("message", cause);
		assertThat(result).hasMessage("message");
		assertThat(result).hasCause(cause);
	}
}
