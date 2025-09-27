package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Extract step: reads the input CSV file and produces a list of {@link Product} rows.
 * <p>
 * Behavior matches Assignment 2 requirements:
 * <ul>
 *   <li>Delimiter is a comma.</li>
 *   <li>First non-empty line is treated as the header and skipped.</li>
 *   <li>Malformed rows are skipped silently (counted in rowsSkipped).</li>
 *   <li>Missing input file triggers a {@link MissingInputException}.</li>
 * </ul>
 */

public class CSVExtractor {
    private int rowsRead = 0;
    private int rowsSkipped = 0;

    /**
     * Reads products from a CSV file.
     *
     * @param inputPath relative path to the input file (e.g., {@code data/products.csv})
     * @return list of parsed {@link Product} objects; may be empty if file only contains a header
     * @throws IOException              if any I/O error occurs while reading
     * @throws MissingInputException    if the input file does not exist
     */
    public List<Product> extract(String inputPath) throws IOException, MissingInputException {
        File f = new File(inputPath);
        if (!f.exists()) {
            throw new MissingInputException("Input file not found at: " + inputPath);
        }

        List<Product> out = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            String line;
            boolean headerSeen = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // First non-empty line is header
                if (!headerSeen) {
                    headerSeen = true;
                    continue;
                }

                rowsRead++;

                String[] parts = line.split(",", -1);
                if (parts.length < 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();

                    out.add(new Product(id, name, price, category));
                } catch (NumberFormatException nfe) {
                    rowsSkipped++;
                }
            }
        }

        return out;
    }

    /** @return number of non-header lines read (attempted) from the file */
    public int getRowsRead() { return rowsRead; }

    /** @return number of rows skipped due to malformed data */
    public int getRowsSkipped() { return rowsSkipped; }
}

