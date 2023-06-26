import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

import static InfoStore.SenderEmail.getEmailAddress;
import static InfoStore.SenderPassword.*;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        // TODO: I am stupid enough to try to store the passwords in a secret keys. Use cipher instead

        String senderEmail = getEmailAddress();
        String senderPassword = getSenderPassword();

        System.out.println("Sender email -> " + senderEmail);
        System.out.println("Sender password -> " + senderPassword);
    }
}
