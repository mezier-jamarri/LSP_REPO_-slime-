package org.howard.edu.lsp.finale.question1;

public interface PasswordAlgorithm {
    /**
     * generate a password of the specified length
     * @param length number of characters
     * @return generated password string
     */
    String generate(int length);
}
