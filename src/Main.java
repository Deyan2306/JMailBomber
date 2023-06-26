import java.io.IOException;

import static InfoStore.SenderEmail.getEmailAddress;
import static Util.Utilities.reader;

public class Main {
    public static void main(String[] args) throws IOException {

        String senderEmail = getEmailAddress();

        System.out.println(senderEmail);

        System.out.print("Enter sender password: ");
        String senderPassword = reader.readLine().trim();

        System.out.println("Sender email -> " + senderEmail);
        System.out.println("Sender password -> " + senderPassword);
    }
}
