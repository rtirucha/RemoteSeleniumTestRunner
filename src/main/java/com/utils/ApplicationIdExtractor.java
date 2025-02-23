package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApplicationIdExtractor {
    public static String patternString = "Generated reference ID:";
    public static String extractAppId(String inputString) {
        String applicationId = null;
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(inputString);
        if (matcher.find()) {
            System.out.println("Pattern found: " + matcher.group());
            int lastMatchedPos = matcher.end();
            applicationId = inputString.substring(lastMatchedPos, lastMatchedPos+7);

        } else {
            System.out.println("Pattern not found.");
        }
        return applicationId;
    }
}
