package publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.gnu.escher.x11.core.X11ClientException;
import org.junit.jupiter.api.Test;

public class X11ClientExceptionTest {
	@Test
	void constructor_Throwable_fails_with_null_cause() {
		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> new X11ClientException((Throwable) null));
		assertThat(exception).hasMessage("cause is marked non-null but is null");
	}

	@Test
	void constructor_Throwable() {
		IOException cause = new IOException();
		X11ClientException exception = new X11ClientException(cause);
		assertThat(exception).hasCause(cause);
	}

	@Test
	void constructor_2_fails_with_null_message() {
		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> new X11ClientException(null, new IOException()));
		assertThat(exception).hasMessage("message is marked non-null but is null");
	}

	@Test
	void constructor_2_fails_with_null_cuase() {
		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> new X11ClientException("message", null));
		assertThat(exception).hasMessage("cause is marked non-null but is null");
	}

	@Test
	void constructor_2() {
		IOException cause = new IOException();
		X11ClientException result = new X11ClientException("message", cause);
		assertThat(result).hasMessage("message");
		assertThat(result).hasCause(cause);
	}

	@Test
	void constructor_3_fails_with_null_message() {
		NullPointerException exception = assertThrows(NullPointerException.class,
				() -> new X11ClientException((String) null));
		assertThat(exception).hasMessage("message is marked non-null but is null");
	}
}
