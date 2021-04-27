package org.gnu.escher.x11;

import static org.gnu.escher.x11.DisplayName.getFromConventionalString;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.moaxcp.xephyr.XephyrRunner;

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
