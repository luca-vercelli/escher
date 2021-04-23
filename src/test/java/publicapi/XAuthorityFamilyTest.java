package publicapi;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import gnu.x11.XAuthority.Family;

public class XAuthorityFamilyTest {
	@Test
	void internetCode() {
		assertThat(Family.INTERNET.getCode()).isEqualTo(0);
	}

	@Test
	void localCode() {
		assertThat(Family.LOCAL.getCode()).isEqualTo(256);
	}

	@Test
	void wildCode() {
		assertThat(Family.WILD.getCode()).isEqualTo(65535);
	}

	@Test
	void krb5principalCode() {
		assertThat(Family.KRB5PRINCIPAL.getCode()).isEqualTo(254);
	}

	@Test
	void localhostCode() {
		assertThat(Family.LOCALHOST.getCode()).isEqualTo(252);
	}

	@Test
	void internet_getByCode() {
		assertThat(Family.getByCode(0)).isEqualTo(Family.INTERNET);
	}

	@Test
	void local_getByCode() {
		assertThat(Family.getByCode(256)).isEqualTo(Family.LOCAL);
	}

	@Test
	void wild_getByCode() {
		assertThat(Family.getByCode(65535)).isEqualTo(Family.WILD);
	}

	@Test
	void krb_getByCode() {
		assertThat(Family.getByCode(254)).isEqualTo(Family.KRB5PRINCIPAL);
	}

	@Test
	void localhost_getByCode() {
		assertThat(Family.getByCode(252)).isEqualTo(Family.LOCALHOST);
	}

	@Test
	void fail_getByCode() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> Family.getByCode(-1234));
		assertThat(exception).hasMessage("Unsupported code \"-1234\"");
	}
}
