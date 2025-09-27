package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Load step: writes transformed {@link Product} rows to a CSV file.
 * <p>
 * Behavior matches Assignment 2 requirements:
 * <ul>
 *   <li>Always writes a header line.</li>
 *   <li>Column order: ProductID,Name,Price,Category,PriceRange</li>
 *   <li>If input list is empty, the output contains only the header.</li>
 * </ul>
 */

public class CSVLoader {
    /**
     * Writes the transformed products to the output CSV file.
     *
     * @param outputPath relative path to the output file (e.g., {@code data/transformed_products.csv})
     * @param products   list of transformed products (may be empty)
     * @throws IOException if any I/O error occurs while writing
     */
    public void load(String outputPath, List<Product> products) throws IOException {
        File outFile = new File(outputPath);
        File parent = outFile.getParentFile();
        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, StandardCharsets.UTF_8, false))) {
            // Header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            // Rows (if any)
            for (Product p : products) {
                bw.write(p.getId() + ","
                        + p.getName() + ","
                        + p.getPrice().toPlainString() + ","
                        + p.getCategory() + ","
                        + p.getPriceRange());
                bw.newLine();
            }
        }
    }
}

