import java.util.Scanner;

public class CaesarCipher {

    // Function to encrypt the message using Caesar Cipher
    public static String encrypt(String message, int shift) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            // Encrypt uppercase letters
            if (ch >= 'A' && ch <= 'Z') {
                ch = (char) (((ch - 'A' + shift) % 26) + 'A');
            }
            // Encrypt lowercase letters
            else if (ch >= 'a' && ch <= 'z') {
                ch = (char) (((ch - 'a' + shift) % 26) + 'a');
            }
            encryptedMessage.append(ch);
        }

        return encryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input
        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = scanner.nextInt();

        // Encrypt the message
        String encryptedMessage = encrypt(message, shift);

        // Output the encrypted message
        System.out.println("Encrypted message: " + encryptedMessage);
    }
}
