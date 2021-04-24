package org.gnu.escher.utils;

public class Validation {

	/**
	 * Throw exception if value is null or contains spaces only
	 * 
	 * @param name
	 * @param value
	 */
	public static String requiresNonBlank(String name, String value) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("name must not be blank.");
		}
		if (value == null || value.trim().isEmpty()) {
			throw new IllegalArgumentException(String.format("%s must not be blank.", name));
		}
		return value;
	}

	/**
	 * Throw exception if value is null
	 * 
	 * @param name
	 * @param value
	 */
	public static Object requiresNonNull(String name, Object value) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("name must not be blank.");
		}
		if (value == null) {
			throw new IllegalArgumentException(String.format("%s must not be null.", name));
		}
		return value;
	}

	/**
	 * Throw exception if value is <0
	 * 
	 * @param name
	 * @param value
	 */
	public static Object requiresNonNegative(String name, int value) {
		if (name == null || name.trim().isEmpty()) {
			throw new IllegalArgumentException("name must not be blank.");
		}
		if (value < 0) {
			throw new IllegalArgumentException(String.format("%s was %d expected >=0.", name, value));
		}
		return value;
	}
}
