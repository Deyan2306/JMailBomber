package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public enum Utilities {
    ;

    // Regular expression for detecting email addresses
    public final static String DETECT_EMAIL_REGEX = ".+@.+\\..+";


    // Buffered reader for reading from the console
    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Regex pattern for checking the email addresses
    public static Pattern emailCheckingPattern = Pattern.compile(Utilities.DETECT_EMAIL_REGEX);


    // Method, which checks if given field is empty
    // @param field - The field to check
    public static boolean isFieldEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
