package gnu.x11;

import com.github.moaxcp.xephyr.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static gnu.x11.DisplayName.*;

public class DisplayConnect {

	private XephyrRunner runner;

	@BeforeEach
	void setup() throws IOException {
		runner = new XephyrRunner(true, true, true, false, false, new ArrayList<>(), Arrays.<String>asList(":1"));
		runner.start();
	}

	@AfterEach
	void teardown() throws InterruptedException {
		runner.stop();
	}

	@Test
	void connect() {
		parse(":1").connect();
	}
}
