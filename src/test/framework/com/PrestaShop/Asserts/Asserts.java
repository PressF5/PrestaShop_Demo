package com.PrestaShop.Asserts;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Asserts {

	public static void assertVariable(String actual, String expected) {

		assertThat(actual, equalTo(expected));
	}

	public static void assertVariable(String actual, String expected, String description) {

		assertThat(description, actual, equalTo(expected));
	}

	public static void assertIgnoringCaseVariable(String actual, String expected) {

		assertThat(actual, equalToIgnoringCase(expected));
	}

	public static void assertContainsVariable(String actual, String expected, String description) {

		assertThat(description, actual, containsString(expected));
	}
	
	public static void assertContainsVariable(String actual, String expected) {

		assertThat(actual, containsString(expected));
	}

	public static void assertEqualVariable(Integer actual, Integer expected, String description) {

		assertThat(description, actual, equalTo(expected));
	}

	public static void assertContainsVariable(List<String> actual, String expected) {

		assertThat(expected, isIn(actual));
	}
}
