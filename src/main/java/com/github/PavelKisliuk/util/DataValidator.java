package com.github.PavelKisliuk.util;

public class DataValidator {
	public static final int MAX_NAME_LENGTH = 64;
	public static final int MAX_AGE_LENGTH = 3;

	public static boolean isNumeric(String s) {
		for(char ch : s.toCharArray()) {
			if(!Character.isDigit(ch)) {
				return false;
			}
		}
		return true;
	}
}