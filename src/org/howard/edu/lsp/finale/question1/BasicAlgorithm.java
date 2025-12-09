package org.howard.edu.lsp.finale.question1;

import java.util.Random;

/**
 * password algorithm that only generates 0-9
 */

public class BasicAlgorithm implements PasswordAlgorithm {
    private final Random random = new Random();
    private static final String DIGITS = "0123456789";

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(DIGITS.length());
            sb.append(DIGITS.charAt(idx));
        }
        return sb.toString();
    }
}
