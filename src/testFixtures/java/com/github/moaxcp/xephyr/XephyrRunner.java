package com.github.moaxcp.xephyr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class XephyrRunner {
	private final boolean br;
	private final boolean ac;
	private final boolean noreset;
	private final Boolean iglx;
	private final boolean glamor;
	private final List<String> enableExtensions;
	private final List<String> args;
	private Process process;

	public XephyrRunner(boolean br, boolean ac, boolean noreset, Boolean iglx, boolean glamor,
			List<String> enableExtensions, List<String> args) {
		this.br = br;
		this.ac = ac;
		this.noreset = noreset;
		this.iglx = iglx;
		this.glamor = glamor;
		this.enableExtensions = enableExtensions;
		this.args = args;
	}

	public void start() throws IOException {
		List<String> command = new ArrayList<>();
		command.add("Xephyr");
		if (br) {
			command.add("-br");
		}
		if (ac) {
			command.add("-ac");
		}
		if (noreset) {
			command.add("-noreset");
		}
		if (iglx != null) {
			if (iglx) {
				command.add("+iglx");
			} else {
				command.add("-iglx");
			}
		}
		if (glamor) {
			command.add("-glamor");
		}
		for (String extension : enableExtensions) {
			command.add("+extension");
			command.add(extension);
		}

		command.addAll(args);

		process = new ProcessBuilder(command.toArray(new String[command.size()])).start();
	}

	public void stop() throws InterruptedException {
		process.destroy();
		process.waitFor(10000, TimeUnit.SECONDS);
		process.destroyForcibly();
		process.waitFor();
	}
}
