

public class HillCipher {
    private static final int[][] KEY = { { 3, 3 }, { 2, 5 } };
    private static final int[][] INVERSE_KEY = { { 15, 17 }, { 20, 9 } }; // Pre-calculated inverse matrix modulo 26

    public static String encrypt(String text) {
        return transform(text, KEY);
    }

    public static String decrypt(String text) {
        return transform(text, INVERSE_KEY);
    }

    private static String transform(String text, int[][] matrix) {
        text = text.replaceAll("[^A-Z]", "").toUpperCase();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            int[] vector = { text.charAt(i) - 'A', text.charAt(i + 1) - 'A' };
            int[] transformed = multiply(matrix, vector);
            result.append((char) (transformed[0] % 26 + 'A')).append((char) (transformed[1] % 26 + 'A'));
        }

        return result.toString();
    }

    private static int[] multiply(int[][] matrix, int[] vector) {
        int[] result = new int[vector.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < vector.length; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String text = "HELLO";
        String paddedText = text.length() % 2 == 0 ? text : text + "X"; // Pad with 'X' if odd length

        String encrypted = encrypt(paddedText);
        String decrypted = decrypt(encrypted);

        System.out.println("Original: " + paddedText);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}