package InfoStore;

import java.io.IOException;
import java.util.regex.Matcher;

import static Util.Utilities.*;

public enum SenderEmail {
    ;

    /* Method, used for reading the email address from the console
     * @throws IOException
     * @throws IllegalArgumentException */
    public static String readEmailAddress() throws IOException, IllegalArgumentException {

        System.out.print("Enter sender email address: ");
        String senderEmail = reader.readLine();

        if (isFieldEmpty(senderEmail)) {
            throw new IllegalArgumentException("The sender email could not be empty!");
        }

        // TODO: Make global if needed
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

    /* Methods, which provides the functionality to read continuously from the console if the
     * provided input is incorrect */
    public static String getEmailAddress() {
        String senderEmail = null;

        do {
            try {
                senderEmail = readEmailAddress();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (IOException e) {
                System.out.println("You provided incorrect input!");
            }
        } while (isFieldEmpty(senderEmail));

        return senderEmail;
    }
}
