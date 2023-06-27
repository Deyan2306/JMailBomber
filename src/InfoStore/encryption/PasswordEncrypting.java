package InfoStore.encryption;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordEncrypting {

    // The encryption algorithm, that the encryptor uses
    public static final String ENCRYPTION_ALGORITHM = "AES";

    // The encryption key (Could change any time)
    private static String ENCRYPTION_KEY = "BOzIBanana!!2314!!#@!$";

    // Private field for the salt
    private static String SALT;

    public PasswordEncrypting() throws NoSuchAlgorithmException {

        // The method auto generates the salt when called
        SALT = generateSalt();
    }

    /* Method for encrypting plain text passwords so they could be stored in a better format
     * @param password - The plaintext representation of the password
     * @throws NoSuchPaddingException - When the padding does not exist
     * @throws NoSuchAlgorithmException - When the provided algorithm does not exist
     * @throws InvalidKeyException - When the provided secret key is invalid
     * @throws IllegalBlockSizeException - When the block size is not equal to one of the sizes, which the secret key uses
     * @throws BadPaddingException - When the provided padding is bad
     * @throws InvalidKeySpecException - When the key specification is invalid */
    public static String encryptPassword(String password) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        SecretKey secretKey = generateKeyFromPassword(ENCRYPTION_KEY, SALT);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(password.getBytes());
        return bytesToHex(encryptedBytes);
    }

    /* Method for decryption of already encrypted password (the encryption should be done with the encryptPassword method)
     * @param encryptedPassword - Already encrypted password
     * @throws NoSuchPaddingException - When the padding does not exist
     * @throws NoSuchAlgorithmException - When the provided algorithm does not exist
     * @throws InvalidKeyException - When the provided secret key is invalid
     * @throws IllegalBlockSizeException - When the block size is not equal to one of the sizes, which the secret key uses
     * @throws BadPaddingException - When the provided padding is bad
     * @throws InvalidKeySpecException - When the key specification is invalid */
    public static String decryptPassword(String encryptedPassword) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException {
        SecretKey secretKey = generateKeyFromPassword(ENCRYPTION_KEY, SALT);
        Cipher cipher = Cipher.getInstance((ENCRYPTION_ALGORITHM));
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(hexToBytes(encryptedPassword));
        return new String(decryptedBytes);
    }

    /* Method for generating a secret key using a password string and salt
     * @param password - String representation of the password (plaintext)
     * @param salt - The salt for the secret key creation
     * @throws InvalidKeySpecException - When the key specification is invalid
     * @throws NoSuchAlgorithmException - When the provided algorithm does not exist */
    public static SecretKey generateKeyFromPassword(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        byte[] keyBytes = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(keyBytes, ENCRYPTION_ALGORITHM);
    }

    /* Method for converting bytes array into hex representation in string
    * @param bytes - a byte array containing the bytes for the conversion */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexValue = new StringBuilder();
        for (byte currentByte : bytes) {
            hexValue.append(String.format("%02x", currentByte));
        }

        return hexValue.toString();
    }

    /* Method for converting a string containing hex values into byte array
     * @param hex - A string containing the hex values */
    public static byte[] hexToBytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(
                    hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    /* Method for generating the "salt" for the password encryption
     * @throws NoSuchAlgorithmException - When the provided algorithm does not exist */
    public static String generateSalt() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }

    /* Method for changing the provided encryption key
    * @param newEncryptionKey - The new encryption key */
    public static void changeEncryptionKey(String newEncryptionKey) {
        ENCRYPTION_KEY = newEncryptionKey;
    }
}
