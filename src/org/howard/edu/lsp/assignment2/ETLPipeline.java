package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ETLPipeline {
    private static final String INPUT  = "data/products.csv";
    private static final String OUTPUT = "data/transformed_products.csv";

    /** Simple in-memory row model */
    private static class Row {
        int id;
        String name;
        BigDecimal price;
        String category;
        String originalCategory; // keep original for rule 3

        Row(int id, String name, BigDecimal price, String category) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.category = category;
            this.originalCategory = category;
        }
    }

    public static void main(String[] args) {
        ETLPipeline app = new ETLPipeline();
        List<Row> rows;

        // Extract 
        try {
            rows = app.extract(INPUT);
        } catch (MissingInput e) {
            System.err.println("ERROR: Input file not found at '" + INPUT + "'.");
            System.err.println("Run the program from the project root so relative paths resolve,");
            System.err.println("and make sure data/products.csv exists next to src/.");
            return;
        } catch (IOException e) {
            System.err.println("ERROR reading input file: " + e.getMessage());
            return;
        }

        // Transform 
        List<Row> transformed = app.transform(rows);

        // Load 
        try {
            app.load(OUTPUT, transformed);
        } catch (IOException e) {
            System.err.println("ERROR writing output file: " + e.getMessage());
            return;
        }

        // Summary
        System.out.println("ETL Summary");
        System.out.println("-----------");
        System.out.println("Rows read        : " + rows.size());
        System.out.println("Rows transformed : " + transformed.size());
        System.out.println("Rows skipped     : 0"); // per spec we simply skip malformed lines silently
        System.out.println("Output written to: " + OUTPUT);
    }

    // EXTRACT 

    private static class MissingInput extends Exception { /* marker */ }

    /**
     * Reads CSV: ProductID,Name,Price,Category (comma delimiter, first line header).
     * Minimal parser 
     */
    private List<Row> extract(String inputPath) throws IOException, MissingInput {
        File f = new File(inputPath);
        if (!f.exists()) throw new MissingInput();

        List<Row> out = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(f, StandardCharsets.UTF_8))) {
            String line;
            boolean headerSeen = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                // First non-empty line is the header: skip it
                if (!headerSeen) {
                    headerSeen = true;
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length < 4) {
                    // Malformed row = skip 
                    continue;
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    BigDecimal price = new BigDecimal(parts[2].trim());
                    String category = parts[3].trim();
                    out.add(new Row(id, name, price, category));
                } catch (NumberFormatException nfe) {
                    // Bad number = skip 
                }
            }
        }

        return out;
    }

    // TRANSFORM 

    private List<Row> transform(List<Row> in) {
        List<Row> out = new ArrayList<>();

        for (Row r : in) {
            // uppercase name
            r.name = (r.name == null ? "" : r.name.toUpperCase());

            // 10% discount if original category is Electronics; always round HALF_UP to 2 dp
            if ("Electronics".equalsIgnoreCase(r.originalCategory)) {
                r.price = round2(r.price.multiply(new BigDecimal("0.90")));
            } else {
                r.price = round2(r.price);
            }

            // 3 recategorize to Premium Electronics if post-discount price > 500 AND original cat Electronics
            if ("Electronics".equalsIgnoreCase(r.originalCategory)
                    && r.price.compareTo(new BigDecimal("500.00")) > 0) {
                r.category = "Premium Electronics";
            }

            // 4 PriceRange is derived on write; nothing else to store here
            out.add(r);
        }

        return out;
    }

    // LOAD 

    private void load(String outputPath, List<Row> rows) throws IOException {
        File outFile = new File(outputPath);
        File parent = outFile.getParentFile();
        if (parent != null && !parent.exists()) parent.mkdirs();

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outFile, StandardCharsets.UTF_8, false))) {
            // Header
            bw.write("ProductID,Name,Price,Category,PriceRange");
            bw.newLine();

            // If input was empty (header only), we still write header and stop (per spec)
            for (Row r : rows) {
                String priceRange = priceRange(r.price);
                bw.write(r.id + ","
                        + r.name + ","
                        + r.price.toPlainString() + ","
                        + r.category + ","
                        + priceRange);
                bw.newLine();
            }
        }
    }

    // HELPERS 

    private static BigDecimal round2(BigDecimal v) {
        return v.setScale(2, RoundingMode.HALF_UP);
    }

    /** Derive PriceRange from FINAL price (inclusive bounds per spec). */
    private static String priceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("0.00")) >= 0 &&
            price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("10.00")) > 0 &&
                   price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("100.00")) > 0 &&
                   price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}

