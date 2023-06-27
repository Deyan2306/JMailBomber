package InfoStore;

import java.io.IOException;

import static Util.Utilities.reader;

public enum SenderPassword {
    ;

    // Method for reading the sender password from the console
    public static String getSenderPassword() throws IOException {

        System.out.print("Enter sender password [default - no password]: ");
        String password = reader.readLine().trim();

        // TODO: Encrypt the password and store it

        return password;
    }

}
