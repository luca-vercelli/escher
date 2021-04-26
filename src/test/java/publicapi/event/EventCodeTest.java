package publicapi.event;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gnu.escher.x11.enums.EventCode.KEY_PRESS;
import static org.gnu.escher.x11.enums.EventCode.UNKNOWN;

import org.gnu.escher.x11.enums.EventCode;
import org.junit.jupiter.api.Test;

public class EventCodeTest {
	@Test
	void of_with_each_code() {
		for (EventCode code : EventCode.values()) {
			assertThat(EventCode.of(code.getCode())).isEqualTo(code);
		}
	}

	@Test
	void of_LAST_EVENT() {
		assertThat(EventCode.of(1000)).isEqualTo(UNKNOWN);
	}

	@Test
	void and() {
		assertThat(KEY_PRESS.and(KEY_PRESS.getCode())).isEqualTo(KEY_PRESS);
	}
}
