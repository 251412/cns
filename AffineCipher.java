import java.util.Scanner;

public class AffineCipher {

    // Function to encrypt the message using Affine Cipher
    public static String encrypt(String message, int a, int b) {
        StringBuilder encryptedMessage = new StringBuilder();

        for (int i = 0; i < message.length(); i++) {
            char ch = message.charAt(i);

            if (Character.isLetter(ch)) {
                int x = Character.toLowerCase(ch) - 'a'; // Convert to number (0-25)
                int c = (a * x + b) % 26; // Affine encryption formula

                if (Character.isUpperCase(ch)) {
                    encryptedMessage.append((char) (c + 'A'));
                } else {
                    encryptedMessage.append((char) (c + 'a'));
                }
            } else {
                encryptedMessage.append(ch); // Non-alphabetic characters
            }
        }
        return encryptedMessage.toString();
    }

    // Function to decrypt the message using Affine Cipher
    public static String decrypt(String encryptedMessage, int a, int b) {
        StringBuilder decryptedMessage = new StringBuilder();

        // Find modular multiplicative inverse of 'a' mod 26
        int aInverse = 0;
        for (int i = 1; i < 26; i++) {
            if ((a * i) % 26 == 1) {
                aInverse = i;
                break;
            }
        }

        for (int i = 0; i < encryptedMessage.length(); i++) {
            char ch = encryptedMessage.charAt(i);

            if (Character.isLetter(ch)) {
                int c = Character.toLowerCase(ch) - 'a'; // Convert to number (0-25)
                int p = (aInverse * (c - b + 26)) % 26; // Affine decryption formula

                if (Character.isUpperCase(ch)) {
                    decryptedMessage.append((char) (p + 'A'));
                } else {
                    decryptedMessage.append((char) (p + 'a'));
                }
            } else {
                decryptedMessage.append(ch); // Non-alphabetic characters
            }
        }
        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Constants for Affine Cipher (a=3, b=12)
        int a = 3; // 'a' in equation C = 3x + 12
        int b = 12; // 'b' in equation C = 3x + 12

        System.out.print("Enter the message to encrypt: ");
        String message = scanner.nextLine();

        // Encrypt the message
        String encryptedMessage = encrypt(message, a, b);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decrypt the message
        String decryptedMessage = decrypt(encryptedMessage, a, b);
        System.out.println("Decrypted message: " + decryptedMessage);
    }
}