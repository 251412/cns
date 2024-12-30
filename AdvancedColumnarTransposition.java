import java.util.Arrays;
import java.util.Scanner;

public class AdvancedColumnarTransposition {

    // Method to encrypt using a single columnar transposition
    public static String singleTransposition(String text, String key) {
        int[] keyOrder = getKeyOrder(key);
        int rows = (int) Math.ceil((double) text.length() / key.length());
        char[][] grid = new char[rows][key.length()];

        // Fill the grid with the text
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                grid[i][j] = (index < text.length()) ? text.charAt(index++) : 'X';
            }
        }

        // Read columns based on the key order
        StringBuilder result = new StringBuilder();
        for (int col : keyOrder) {
            for (int i = 0; i < rows; i++) {
                result.append(grid[i][col]);
            }
        }

        return result.toString();
    }

    // Method to decrypt using a single columnar transposition
    public static String singleDecryption(String text, String key) {
        int[] keyOrder = getKeyOrder(key);
        int rows = text.length() / key.length();
        char[][] grid = new char[rows][key.length()];

        // Fill the grid column by column based on key order
        int index = 0;
        for (int col : keyOrder) {
            for (int i = 0; i < rows; i++) {
                grid[i][col] = text.charAt(index++);
            }
        }

        // Read rows to reconstruct the plaintext
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                result.append(grid[i][j]);
            }
        }

        return result.toString();
    }

    // Method to compute the column order from the key
    private static int[] getKeyOrder(String key) {
        int[] order = new int[key.length()];
        Character[] chars = new Character[key.length()];
        for (int i = 0; i < key.length(); i++) chars[i] = key.charAt(i);
        Arrays.sort(chars, (a, b) -> a.compareTo(b));
        for (int i = 0; i < chars.length; i++) order[i] = key.indexOf(chars[i]);
        return order;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take inputs from the user
        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine();
        System.out.print("Enter the first key: ");
        String key1 = sc.nextLine();
        System.out.print("Enter the second key: ");
        String key2 = sc.nextLine();

        // Perform double encryption
        String intermediateCipher = singleTransposition(plaintext, key1);
        String finalCipher = singleTransposition(intermediateCipher, key2);

        System.out.println("Ciphertext: " + finalCipher);

        // Perform double decryption
        String intermediatePlain = singleDecryption(finalCipher, key2);
        String originalPlain = singleDecryption(intermediatePlain, key1);

        System.out.println("Decrypted Text: " + originalPlain);

        sc.close();
    }
}
