package org.howard.edu.lsp.midterm.question2;

public class Main {
    public static void main(String[] args) {
        // Invoke all overloaded methods statically
        System.out.println("Circle radius 3.0 → area = " + AreaCalculator.area(3.0));
        System.out.println("Rectangle 5.0 x 2.0 → area = " + AreaCalculator.area(5.0, 2.0));
        System.out.println("Triangle base 10, height 6 → area = " + AreaCalculator.area(10, 6));
        System.out.println("Square side 4 → area = " + AreaCalculator.area(4));

        // Demonstrate exception handling
        try {
            System.out.println("Testing invalid input:");
            System.out.println("Circle radius -2.0 → area = " + AreaCalculator.area(-2.0));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /*
     * Explanation:
     * Method overloading is the better design choice because it allows
     * multiple methods with the same name ("area") to handle different
     * parameter types and shapes, improving readability and organization.
     * Using separate names (e.g., circleArea, rectangleArea) would clutter
     * the API and make the code less elegant and cohesive.
     */
}

