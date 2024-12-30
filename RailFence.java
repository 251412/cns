import java.util.Scanner;
public class RailFence {
    public static String encrypt(String plaintext, int depth) {
        char[][] rail = new char[depth][plaintext.length()];
        boolean down = false;
        int row = 0, col = 0;
        for (int i = 0; i < plaintext.length(); i++) {
            if (row == 0 || row == depth - 1) down = !down;
            rail[row][col++] = plaintext.charAt(i);
            row += down ? 1 : -1;
        }
        StringBuilder ciphertext = new StringBuilder();
        for (char[] r : rail) {
            for (char c : r) {
                if (c != 0) ciphertext.append(c);
            }
        }
        return ciphertext.toString();
    }
    public static String decrypt(String ciphertext, int depth) {
        char[][] rail = new char[depth][ciphertext.length()];
        boolean down = false;
        int row = 0, col = 0;
        // Mark the positions to be filled
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0 || row == depth - 1) down = !down;
            rail[row][col++] = '*';
            row += down ? 1 : -1;
        }
        // Fill the marked positions with ciphertext characters
        int index = 0;
        for (int i = 0; i < depth; i++) {
            for (int j = 0; j < ciphertext.length(); j++) {
                if (rail[i][j] == '*' && index < ciphertext.length()) {
                    rail[i][j] = ciphertext.charAt(index++);
                }
            }
        }
        // Read the characters in zig-zag order
        StringBuilder plaintext = new StringBuilder();
        row = 0;
        col = 0;
        down = false;
        for (int i = 0; i < ciphertext.length(); i++) {
            if (row == 0 || row == depth - 1) down = !down;
            plaintext.append(rail[row][col++]);
            row += down ? 1 : -1;
        }
        return plaintext.toString();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String plaintext = sc.nextLine();
        System.out.print("Enter depth: ");
        int depth = sc.nextInt();
        String ciphertext = encrypt(plaintext, depth);
        System.out.println("Ciphertext: " + ciphertext);
        String decryptedText = decrypt(ciphertext, depth);
        System.out.println("Decrypted text: " + decryptedText);
        sc.close();
    }
}