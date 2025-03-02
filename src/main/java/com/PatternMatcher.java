/**
 * 
 */
package com;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 */
public class PatternMatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] passTestCaseConsoleOutput = { "Email Addess eneterd: raj.challa@exeter.com", "printing scenario status --> passed" };
		String[] failedTestCaseConsoleOutput = { "Email Addess eneterd: raj.challa@exeter.com", "printing scenario status --> failed" };
		/*
		 * String emailPattern = "[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,6}";
		 * Pattern pattern = Pattern.compile(emailPattern); Matcher matcher =
		 * pattern.matcher("Email Addess eneterd: raj.challa@exeter.com");
		 * matcher.groupCount(); matcher.regionEnd();
		 * 
		 * matcher.find();
		 */

		// String input = "Contact us at support@example.com for more information.";
		String failedTestResponse = extractTestCaseResult(failedTestCaseConsoleOutput);
		System.out.println(failedTestResponse);
		String paasedTestResponse = extractTestCaseResult(passTestCaseConsoleOutput);
		System.out.println(paasedTestResponse);

	}

	private static String extractTestCaseResult(String[] consoleOutput) {

		String testResult = null;
		
		String email = null;
		String appId= "12345";
		for (String consoleOutputLine : consoleOutput) {
			
			String emailInString = patternMatcher(consoleOutputLine,
					"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}");
			if (emailInString != null) {
				email = emailInString;
				System.out.println("Found email: " + email);
			}

			
			String failTestStringPattern = "scenario status --> failed";

			String failedTestString = null;
			boolean isFailedStringFound = ((failedTestString = patternMatcher(consoleOutputLine,
					failTestStringPattern)) != null);
			if (isFailedStringFound)
			{
				System.out.println("Found faile test : " + failedTestString);
				return "TEST CASE FAILED";
			}

		
			String passTestStringPattern = "scenario status --> passed";

			String passedTestString = null;
			boolean isPassedStringFound = ((passedTestString = patternMatcher(consoleOutputLine,
					passTestStringPattern)) != null);
			if (isPassedStringFound) {
				System.out.println("Found faile test : " + passedTestString);
				return "TEST CASE PASSED " + appId +"," + email; 
			}
		}
		return testResult;
	}

	private static String patternMatcher(String input, String patternString) {

		Pattern pattern = Pattern.compile(patternString);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find())
			return matcher.group();
		return null;
	}

}
