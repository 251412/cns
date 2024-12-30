import java.util.*;

public class PlayfairCipher {

    private static final int SIZE = 5;  // Size of Playfair matrix (5x5)
    private static char[][] keyMatrix = new char[SIZE][SIZE];
    private static String key = "ldrp";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the plaintext (only alphabets): ");
        String plaintext = scanner.nextLine().toLowerCase().replaceAll("[^a-z]", "");

        // Prepare the key matrix
        generateKeyMatrix(key);

        // Encrypt the plaintext
        String ciphertext = encrypt(plaintext);

        System.out.println("Ciphertext: " + ciphertext);
    }

    // Generate the Playfair cipher matrix using the given key
    private static void generateKeyMatrix(String key) {
        Set<Character> usedChars = new HashSet<>();
        StringBuilder keyWithDuplicatesRemoved = new StringBuilder();

        // Remove duplicate characters from the key
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (!usedChars.contains(ch)) {
                usedChars.add(ch);
                keyWithDuplicatesRemoved.append(ch);
            }
        }

        // Add the remaining letters of the alphabet that are not in the key
        for (char ch = 'a'; ch <= 'z'; ch++) {
            if (!usedChars.contains(ch) && ch != 'j') { // Exclude 'j' as it is combined with 'i'
                keyWithDuplicatesRemoved.append(ch);
            }
        }

        // Fill the matrix with the modified key
        int k = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                keyMatrix[i][j] = keyWithDuplicatesRemoved.charAt(k++);
            }
        }
    }

    // Encrypt the plaintext using Playfair cipher
    private static String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        // Prepare the plaintext (if necessary, split identical adjacent letters and add 'x')
        plaintext = preparePlaintext(plaintext);

        // Encrypt each digraph
        for (int i = 0; i < plaintext.length(); i += 2) {
            char firstChar = plaintext.charAt(i);
            char secondChar = plaintext.charAt(i + 1);

            int[] firstPos = getPosition(firstChar);
            int[] secondPos = getPosition(secondChar);

            if (firstPos[0] == secondPos[0]) {  // Same row
                ciphertext.append(keyMatrix[firstPos[0]][(firstPos[1] + 1) % SIZE]);
                ciphertext.append(keyMatrix[secondPos[0]][(secondPos[1] + 1) % SIZE]);
            } else if (firstPos[1] == secondPos[1]) {  // Same column
                ciphertext.append(keyMatrix[(firstPos[0] + 1) % SIZE][firstPos[1]]);
                ciphertext.append(keyMatrix[(secondPos[0] + 1) % SIZE][secondPos[1]]);
            } else {  // Rectangle case
                ciphertext.append(keyMatrix[firstPos[0]][secondPos[1]]);
                ciphertext.append(keyMatrix[secondPos[0]][firstPos[1]]);
            }
        }

        return ciphertext.toString();
    }

    // Prepare the plaintext by making it fit the Playfair cipher rules
    private static String preparePlaintext(String plaintext) {
        StringBuilder preparedText = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            char ch = plaintext.charAt(i);
            preparedText.append(ch);
            if (i + 1 < plaintext.length() && plaintext.charAt(i) == plaintext.charAt(i + 1)) {
                preparedText.append('x');  // Insert 'x' if there are duplicate letters
            }
        }

        // If the plaintext has an odd number of characters, append 'x' at the end
        if (preparedText.length() % 2 != 0) {
            preparedText.append('x');
        }

        return preparedText.toString();
    }

    // Get the position (row, column) of a character in the key matrix
    private static int[] getPosition(char ch) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (keyMatrix[i][j] == ch) {
                    return new int[]{i, j};
                }
            }
        }
        return null;  // This shouldn't happen if the character is valid
    }
}
