package org.howard.edu.lsp.finale.question1;
/**
 * main service responsible for managing password generation
 * enforces a single instance and swappable algorithms
 */
public class PasswordGeneratorService {

    // singleton instance
    private static PasswordGeneratorService instance;

    // strategy reference
    private PasswordAlgorithm algorithm;

    /**
     * PRIVATE constructor prevents instantiation
     */
    private PasswordGeneratorService() {}

    /**
     * returns the single instance of this service
     * @return PasswordGeneratorService instance
     */
    public static PasswordGeneratorService getInstance() {
        if (instance == null) {
            instance = new PasswordGeneratorService();
        }
        return instance;
    }

    /**
     * select the password algorithm by name.
     * @param name "basic", "enhanced", or "letters"
     */
    public void setAlgorithm(String name) {
        switch (name.toLowerCase()) {
            case "basic":
                this.algorithm = new BasicAlgorithm();
                break;
            case "enhanced":
                this.algorithm = new EnhancedAlgorithm();
                break;
            case "letters":
                this.algorithm = new LettersAlgorithm();
                break;
            default:
                throw new IllegalArgumentException("Unsupported algorithm: " + name);
        }
    }

    /**
     * generate password using current algorithm.
     * @param length number of characters
     * @return generated password String
     * @throws IllegalStateException if algorithm not selected first
     */
    public String generatePassword(int length) {
        if (algorithm == null) {
            throw new IllegalStateException("Algorithm not selected!");
        }
        return algorithm.generate(length);
    }

    /*
     
     DESIGN PATTERN DOCUMENTATION:
     
     Patterns Used:
     - Singleton Pattern:
         ensures only ONE shared PasswordGeneratorService instance exists

     - Strategy Pattern:
         allows password generation algorithms to be selected,
         swapped, and expanded at runtime without changing client code

     Why:
     - supports multiple password-generation behaviors (strategies)
     - behavior can be changed dynamically via setAlgorithm()
     - future algorithms can be added independently
     - ensures system-wide shared access to the single service instance
     
     */
}