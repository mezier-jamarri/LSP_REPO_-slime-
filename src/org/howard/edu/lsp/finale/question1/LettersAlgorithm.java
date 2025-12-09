package org.howard.edu.lsp.finale.question1;

import java.security.SecureRandom;

/**
 * password algorithm that only generates A-Z and a-z characters
 */
public class LettersAlgorithm implements PasswordAlgorithm {
    private final SecureRandom random = new SecureRandom();
    private static final String LETTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "abcdefghijklmnopqrstuvwxyz";

    @Override
    public String generate(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(LETTERS.length());
            sb.append(LETTERS.charAt(idx));
        }
        return sb.toString();
    }
}