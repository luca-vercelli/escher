package org.gnu.escher.app;

import java.io.*;
import java.util.*;

/** Classpath utility. */
public class Classpath {
	public static final String CLASSPATH = System.getProperty("java.class.path");

	public static final File[] CLASSPATH_DIRS;
	static {
		StringTokenizer st = new StringTokenizer(CLASSPATH, File.pathSeparator);
		int count = st.countTokens();
		CLASSPATH_DIRS = new File[count];
		for (int i = 0; i < count; i++)
			CLASSPATH_DIRS[i] = new File(st.nextToken());
	}

	/**
	 * Find a class file in default classpath.
	 * 
	 * @see #findFile(File[], String)
	 */
	public static File findClass(String name) {
		return findFile(toClassFilename(name));
	}

	/**
	 * Find a class file given search path directories.
	 * 
	 * @see #findFile(File[], String)
	 */
	public static File findClass(File[] dirs, String name) {
		return findFile(dirs, toClassFilename(name));
	}

	/**
	 * Find a plain file or a directory in default classpath.
	 * 
	 * @see #findFile(File[], String)
	 */
	public static File findFile(String name) {
		return findFile(CLASSPATH_DIRS, name);
	}

	/**
	 * Find a plain file or a directory.
	 *
	 * @param dirs search paths
	 * @param name filename (basename with extension) or dirname
	 * @return <code>null</code> if not found
	 */
	public static File findFile(File[] dirs, String name) {
		for (int i = 0; i < dirs.length; i++) {
			File file = new File(dirs[i], name);
			if (file.canRead())
				return file;
		}
		return null;
	}

	/**
	 * Find all class files of a package in default classpath.
	 * 
	 * @see #findPackage(File[], String)
	 */
	public static File[] findPackage(String name) {
		return findPackage(CLASSPATH_DIRS, name);
	}

	/**
	 * Find all class files of a package in given search path directories.
	 * 
	 * @see #findFile(File[], String)
	 */
	public static File[] findPackage(File[] dirs, String name) {
		File package_file = findFile(dirs, toFilename(name));
		return package_file.listFiles(new java.io.FilenameFilter() {
			public boolean accept(File dir, String filename) {
				return filename.endsWith(".class");
			}
		});
	}

	/**
	 * Convert a JVM class name to a class filename.
	 */
	public static String toClassFilename(String name) {
		return toFilename(name) + ".class";
	}

	/**
	 * Convert a JVM name to a filename.
	 */
	public static String toFilename(String name) {
		return name.replace('.', File.separatorChar);
	}

	/**
	 * Get the JVM class name of a file.
	 */
	public static String toJvmName(String package_name, File file) {
		String filename = file.getName();
		String basename = filename.substring(0, filename.length() - ".class".length());
		return package_name + "." + basename;
	}

	/**
	 * Get a list of JVM class names from a list of class files.
	 */
	public static String[] toJvmName(String package_name, File[] files) {

		String[] names = new String[files.length];
		for (int i = 0; i < files.length; i++)
			names[i] = toJvmName(package_name, files[i]);
		return names;
	}
}
