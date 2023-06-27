package InfoStore;

import java.io.IOException;
import java.util.regex.Matcher;

import static Util.Utilities.*;

public enum SenderEmail {
    ;

    /* Method, used for reading the email address from the console
     * @param type - Specifies whose is the email address (sender / receiver)
     * @throws IOException
     * @throws IllegalArgumentException */
    public static String readEmailAddress(String type) throws IOException, IllegalArgumentException {

        System.out.printf("Enter %s email address: ", type);
        String emailAddress = reader.readLine();

        if (isFieldEmpty(emailAddress)) {
            throw new IllegalArgumentException(String.format("The %s email could not be empty!", type));
        }

        // TODO: Make global if needed
        Matcher matcher = emailCheckingPattern.matcher(emailAddress);

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
     * provided input is incorrect
     * @param type - Specifies whose is the email address (sender / receiver) */
    public static String getEmailAddress(String type) {
        String senderEmail = null;

        do {
            try {
                senderEmail = readEmailAddress(type);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            } catch (IOException e) {
                System.out.println("You provided incorrect input!");
            }
        } while (isFieldEmpty(senderEmail));

        return senderEmail;
    }
}
