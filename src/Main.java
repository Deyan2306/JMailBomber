import java.io.IOException;

import static InfoStore.SenderEmail.getEmailAddress;
import static InfoStore.SenderPassword.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String senderEmail = getEmailAddress();
        String senderPassword = getSenderPassword();

        System.out.println("Sender email -> " + senderEmail);
        System.out.println("Sender password -> " + senderPassword);
    }
}
