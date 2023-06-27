package EmailSending;

import java.io.IOException;
import java.util.Properties;

import static Util.Utilities.reader;

public class SingleMailSender {

    public static class Mail {
        private String header;
        private String body;

        public Mail(String header, String body) {
            setHeader(header);
            setBody(body);
        }

        public String getHeader() {
            return header;
        }

        public void setHeader(String header) {
            this.header = header;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

    private String mailSender;
    private String mailSenderPasswordHash;
    private String mailReceiver;

    public SingleMailSender() throws IOException {

        System.out.println("Welcome to the mail sender!");
        Mail toSend = createMail();

    }

    public static Mail createMail() throws IOException {
        System.out.println("==== Mail Initialization ====");

        System.out.print("Enter the mail header: ");
        String mailHeader = reader.readLine().trim();

        System.out.print("Enter the mail body: ");
        String mailBody = reader.readLine().trim();

        return new Mail(mailHeader, mailBody);
    }

    public static Properties setUpProperties() {
        Properties properties = new Properties();

        // TODO: Implement the props

        return properties;
    }
}
