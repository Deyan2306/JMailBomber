import InfoStore.encryption.PasswordEncrypting;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static InfoStore.SenderEmail.getEmailAddress;
import static InfoStore.SenderPassword.*;
import static Util.Utilities.RECEIVER;
import static Util.Utilities.SENDER;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException {

        PasswordEncrypting encryptor = new PasswordEncrypting();

        String senderEmail = getEmailAddress(SENDER);
        String senderPassword = getSenderPassword();

        String encryptedPassword = PasswordEncrypting.encryptPassword(senderPassword);
        String decryptedPassword = PasswordEncrypting.decryptPassword(encryptedPassword);

        String receiver = getEmailAddress(RECEIVER);

        System.out.println("-----------------------");
        System.out.println("Sender email:             \t" + senderEmail);
        System.out.println("Encrypted sender password:\t" + encryptedPassword);
        System.out.println("Decrypted sender password:\t" + decryptedPassword);
        System.out.println("----");
        System.out.println("Receiver email address:   \t" + receiver);
    }
}
