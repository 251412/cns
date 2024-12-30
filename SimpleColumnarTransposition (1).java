import java.util.Arrays;
import java.util.Scanner;
public class SimpleColumnarTransposition {
    public static String encrypt(String plaintext, String key) {
        int[] keyOrder = getKeyOrder(key);
        int rows = (int) Math.ceil((double) plaintext.length() / key.length());
        char[][] grid = new char[rows][key.length()];
        // Fill grid with plaintext
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                grid[i][j] = (index < plaintext.length()) ? plaintext.charAt(index++) : 'X';
            }
        }
        // Read columns based on key order
        StringBuilder ciphertext = new StringBuilder();
        for (int col : keyOrder) {
            for (int i = 0; i < rows; i++) {
                ciphertext.append(grid[i][col]);
            }
        }
        return ciphertext.toString();
    }
    public static String decrypt(String ciphertext, String key) {
        int[] keyOrder = getKeyOrder(key);
        int rows = ciphertext.length() / key.length();
        char[][] grid = new char[rows][key.length()];
        // Fill grid column by column based on key order
        int index = 0;
        for (int col : keyOrder) {
            for (int i = 0; i < rows; i++) {
                grid[i][col] = ciphertext.charAt(index++);
            }
        }
        // Read rows to get plaintext
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.length(); j++) {
                plaintext.append(grid[i][j]);
            }
        }
        return plaintext.toString();
    }
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
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();
        System.out.print("Enter key: ");
        String key = sc.nextLine();
        String ciphertext = encrypt(plaintext, key);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(ciphertext, key);
        System.out.println("Decrypted text: " + decryptedText);
        sc.close();
    }
}