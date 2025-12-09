package org.howard.edu.lsp.finale.question1;
import java.security.SecureRandom;

/**
 * password algorithm that supports letters and digits
 */
public class EnhancedAlgorithm implements PasswordAlgorithm {
    private final SecureRandom random = new SecureRandom();
    private static final String ALLOWED =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz" +
            "0123456789";

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(ALLOWED.length());
            sb.append(ALLOWED.charAt(idx));
        }
        return sb.toString();
    }
}