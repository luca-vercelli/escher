package org.gnu.escher.app;

import com.github.moaxcp.xephyr.*;

import org.gnu.escher.app.Speedy;
import org.gnu.escher.app.WinOp;
import org.gnu.escher.app.displayhack.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	private static final String[] args = new String[] { "--display", ":1" };

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
	void speedy() {
		Speedy.main(args);
	}

	@Test
	void winOp() {
		WinOp.main(args);
	}

	@Test
	void deco() {
		Deco.main(args);
	}

	@Test
	void munch() {
		Munch.main(args);
	}

	@Test
	void rorshach() {
		Rorschach.main(args);
	}

	@Test
	void squiral() {
		Squiral.main(args);
	}

	@Test
	void sprites() throws Exception {
		Sprites.main(args);
	}
}
