import java.util.HashMap;
import java.util.Map;

public class SubstitutionCipher{
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SUBSTITUTION = "QWERTYUIOPLKJHGFDSAZXCVBNM";

    public static String encrypt(String text) {
        Map<Character, Character> map = createMap(ALPHABET, SUBSTITUTION);
        return transform(text, map);
    }

    public static String decrypt(String text) {
        Map<Character, Character> map = createMap(SUBSTITUTION, ALPHABET);
        return transform(text, map);
    }

    private static Map<Character, Character> createMap(String from, String to) {
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < from.length(); i++) {
            map.put(from.charAt(i), to.charAt(i));
        }
        return map;
    }

    private static String transform(String text, Map<Character, Character> map) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toUpperCase().toCharArray()) {
            result.append(map.getOrDefault(character, character));
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String text = "HELLO WORLD";
        String encrypted = encrypt(text);
        String decrypted = decrypt(encrypted);

        System.out.println("Original: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}