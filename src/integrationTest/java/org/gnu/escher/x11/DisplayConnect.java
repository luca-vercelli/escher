package org.gnu.escher.x11;

import com.github.moaxcp.xephyr.*;
import org.junit.jupiter.api.*;

import static org.gnu.escher.x11.DisplayName.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
		getFromConventionalString(":1").connect();
	}
}
