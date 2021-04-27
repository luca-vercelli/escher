package publicapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gnu.escher.utils.Validation.requiresNonBlank;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class ValidationsTest {
	@Test
	void requiresNonNull_null_name_fails() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> requiresNonBlank(null, "value"));
		assertThat(exception).hasMessage("name must not be blank");
	}

	@Test
	void requiresNonNull_null_value_fails() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> requiresNonBlank("variable", null));
		assertThat(exception).hasMessage("variable must not be blank");
	}

	@Test
	void requiresNonNull_empty_name_fails() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> requiresNonBlank("", "value"));
		assertThat(exception).hasMessage("name must not be blank");
	}

	@Test
	void requiresNonNull_empty_value_fails() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> requiresNonBlank("variable", ""));
		assertThat(exception).hasMessage("variable must not be blank");
	}
}
