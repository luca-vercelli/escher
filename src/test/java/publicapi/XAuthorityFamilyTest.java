package publicapi;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.gnu.escher.x11.enums.XAuthorityFamily;
import org.junit.jupiter.api.Test;

public class XAuthorityFamilyTest {
	@Test
	void internetCode() {
		assertThat(XAuthorityFamily.INTERNET.getCode()).isEqualTo(0);
	}

	@Test
	void localCode() {
		assertThat(XAuthorityFamily.LOCAL.getCode()).isEqualTo(256);
	}

	@Test
	void wildCode() {
		assertThat(XAuthorityFamily.WILD.getCode()).isEqualTo(65535);
	}

	@Test
	void krb5principalCode() {
		assertThat(XAuthorityFamily.KRB5PRINCIPAL.getCode()).isEqualTo(254);
	}

	@Test
	void localhostCode() {
		assertThat(XAuthorityFamily.LOCALHOST.getCode()).isEqualTo(252);
	}

	@Test
	void internet_getByCode() {
		assertThat(XAuthorityFamily.getByCode(0)).isEqualTo(XAuthorityFamily.INTERNET);
	}

	@Test
	void local_getByCode() {
		assertThat(XAuthorityFamily.getByCode(256)).isEqualTo(XAuthorityFamily.LOCAL);
	}

	@Test
	void wild_getByCode() {
		assertThat(XAuthorityFamily.getByCode(65535)).isEqualTo(XAuthorityFamily.WILD);
	}

	@Test
	void krb_getByCode() {
		assertThat(XAuthorityFamily.getByCode(254)).isEqualTo(XAuthorityFamily.KRB5PRINCIPAL);
	}

	@Test
	void localhost_getByCode() {
		assertThat(XAuthorityFamily.getByCode(252)).isEqualTo(XAuthorityFamily.LOCALHOST);
	}

	@Test
	void fail_getByCode() {
		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
				() -> XAuthorityFamily.getByCode(-1234));
		assertThat(exception).hasMessage("Unsupported code \"-1234\"");
	}
}
