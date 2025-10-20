package org.howard.edu.lsp.midterm.question2;

public class AreaCalculator {
    public static double area(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Radius must be positive.");
        }
        return Math.PI * radius * radius;
    }

    // Rectangle area
    public static double area(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive.");
        }
        return width * height;
    }

    // Triangle area
    public static double area(int base, int height) {
        if (base <= 0 || height <= 0) {
            throw new IllegalArgumentException("Base and height must be positive.");
        }
        return 0.5 * base * height;
    }

    // Square area
    public static double area(int side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Side length must be positive.");
        }
        return side * side;
    }
}


