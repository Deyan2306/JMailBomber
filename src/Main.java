import Util.Utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static Pattern emailCheckingPattern = Pattern.compile(Utilities.DETECT_EMAIL_REGEX);

    public static void main(String[] args) throws IOException {

        String senderEmail = getEmailAddress();

        System.out.println(senderEmail);

        System.out.print("Enter sender password: ");
        String senderPassword = reader.readLine().trim();

        System.out.println("Sender email -> " + senderEmail);
        System.out.println("Sender password -> " + senderPassword);
    }

    public static String getEmailAddress() {
        String senderEmail = null;

        do {
            try {
                senderEmail = getEmailAddress();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        } while (isFieldEmpty(senderEmail));

        return senderEmail;
    }

    public static String readEmailAddress() throws IOException, IllegalArgumentException {

        // Get the sender email information
        System.out.print("Enter sender email address: ");
        String senderEmail = reader.readLine();

        // Throw an exception if the sender email is empty
        if (isFieldEmpty(senderEmail)) {
            throw new IllegalArgumentException("The sender email could not be empty!");
        }

        Matcher matcher = emailCheckingPattern.matcher(senderEmail);

        String matchedEmail = null;

        while (matcher.find()) {
            matchedEmail = matcher.group();
        }

        if (isFieldEmpty(matchedEmail)) {
            throw new IllegalArgumentException("The provided email address is incorrect!");
        }

        return matchedEmail;
    }

    public static boolean isFieldEmpty(String field) {
        return field == null || field.trim().isEmpty();
    }
}
