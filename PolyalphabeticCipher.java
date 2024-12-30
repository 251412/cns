import java.util.Scanner;

public class PolyalphabeticCipher {

    // Method to encrypt the text using Vigenère Cipher
    public static String encrypt(String text, String key) {
        StringBuilder cipherText = new StringBuilder();
        int keyIndex = 0;

        // Loop through each character in the text
        for (int i = 0; i < text.length(); i++) {
            char currentChar = text.charAt(i);

            // Check if the character is an alphabet
            if (Character.isLetter(currentChar)) {
                // Determine the shift based on the key character
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = Character.toLowerCase(keyChar) - 'a';

                // Encrypt the current character and add it to the cipherText
                if (Character.isUpperCase(currentChar)) {
                    char encryptedChar = (char) ((currentChar - 'A' + shift) % 26 + 'A');
                    cipherText.append(encryptedChar);
                } else {
                    char encryptedChar = (char) ((currentChar - 'a' + shift) % 26 + 'a');
                    cipherText.append(encryptedChar);
                }

                // Move to the next character of the key
                keyIndex++;
            } else {
                // If the character is not an alphabet, just append it unchanged
                cipherText.append(currentChar);
            }
        }
        return cipherText.toString();
    }

    // Method to decrypt the cipher text using Vigenère Cipher
    public static String decrypt(String cipherText, String key) {
        StringBuilder plainText = new StringBuilder();
        int keyIndex = 0;

        // Loop through each character in the cipher text
        for (int i = 0; i < cipherText.length(); i++) {
            char currentChar = cipherText.charAt(i);

            // Check if the character is an alphabet
            if (Character.isLetter(currentChar)) {
                // Determine the shift based on the key character
                char keyChar = key.charAt(keyIndex % key.length());
                int shift = Character.toLowerCase(keyChar) - 'a';

                // Decrypt the current character and add it to the plainText
                if (Character.isUpperCase(currentChar)) {
                    char decryptedChar = (char) ((currentChar - 'A' - shift + 26) % 26 + 'A');
                    plainText.append(decryptedChar);
                } else {
                    char decryptedChar = (char) ((currentChar - 'a' - shift + 26) % 26 + 'a');
                    plainText.append(decryptedChar);
                }

                // Move to the next character of the key
                keyIndex++;
            } else {
                // If the character is not an alphabet, just append it unchanged
                plainText.append(currentChar);
            }
        }
        return plainText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get input from the user
        System.out.print("Enter the plain text: ");
        String text = scanner.nextLine();

        System.out.print("Enter the key: ");
        String key = scanner.nextLine();

        // Encrypt the text
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted Text: " + encryptedText);

        // Decrypt the text
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted Text: " + decryptedText);

        scanner.close();
    }
}
