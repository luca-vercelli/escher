package org.gnu.escher.app;

import java.io.IOException;
import java.util.Arrays;

import org.gnu.escher.app.glxdemo.ABGR;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.moaxcp.xephyr.XephyrRunner;

public class GLXMain {

	private static final String[] args = new String[] { "--display", ":1" };

	private XephyrRunner runner;

	@BeforeEach
	void setup() throws IOException {
		runner = new XephyrRunner(true, true, true, true, true, Arrays.<String>asList("GLX"),
				Arrays.<String>asList(":1"));
		runner.start();
	}

	@AfterEach
	void teardown() throws InterruptedException {
		runner.stop();
	}

	@Test
	void abgr() {
		ABGR.main(args);
	}
}
