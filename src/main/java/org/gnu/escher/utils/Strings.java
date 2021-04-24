package org.gnu.escher.utils;

public class Strings {

	public static String requiresNonBlank(String name, String value) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("name must not be blank.");
		}
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("%s must not be blank.", name));
		}
		return value;
	}
}
