package InfoStore;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import static Util.Utilities.reader;

public enum SenderPassword {
    ;

    private static int ENCODING_ITERATIONS = 65536;
    private static int ENCODING_KEY_LENGTH = 256;

    // Method for reading the sender password from the console
    public static String getSenderPassword() throws IOException {

        System.out.print("Enter sender password [default - no password]: ");
        String password = reader.readLine().trim();

        return password;
    }

    // Method for generating the secret key for hiding the password, so it is not stored plaintext
    // @throws NoSuchAlgorithmException - When the provided algorithm does not exist
    // @throws InvalidKeySpecException - When the provide key specification is invalid
    // @param password - The password that we want to encrypt
    // @param salt - The salt for generating the password
    public static byte[] generateKeyFromPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ENCODING_ITERATIONS, ENCODING_KEY_LENGTH);

        SecretKey generatedKey = factory.generateSecret(spec);
        return generatedKey.getEncoded();
    }

    // Method for generating the salt for storing the passwords in a hashed format
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);
        return salt;
    }
}
