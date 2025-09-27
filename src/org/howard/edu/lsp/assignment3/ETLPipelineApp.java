package org.howard.edu.lsp.assignment3;

import java.io.IOException;
import java.util.List;

/**
 * Entry point and coordinator for the object-oriented ETL pipeline.
 * <p>
 * Responsibilities:
 * <ul>
 *   <li>Define input/output relative paths.</li>
 *   <li>Run Extract → Transform → Load in order.</li>
 *   <li>Print a run summary (rows read, transformed, skipped, output path).</li>
 * </ul>
 */

public class ETLPipelineApp {
    private static final String INPUT  = "data/products.csv";
    private static final String OUTPUT = "data/transformed_products.csv";

    /**
     * Runs the ETL pipeline.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        CSVExtractor extractor = new CSVExtractor();
        Transformer transformer = new Transformer();
        CSVLoader loader = new CSVLoader();

        List<Product> extracted;
        try {
            extracted = extractor.extract(INPUT);
        } catch (MissingInputException mie) {
            System.err.println("ERROR: " + mie.getMessage());
            System.err.println("Run the program from the project root so relative paths resolve,");
            System.err.println("and make sure data/products.csv exists next to src/.");
            return;
        } catch (IOException ioe) {
            System.err.println("ERROR reading input file: " + ioe.getMessage());
            return;
        }

        List<Product> transformed = transformer.transform(extracted);

        try {
            loader.load(OUTPUT, transformed);  // always writes header, even if empty
        } catch (IOException ioe) {
            System.err.println("ERROR writing output file: " + ioe.getMessage());
            return;
        }

        // Summary (matches Assignment 2 expectations)
        System.out.println("ETL Summary");
        System.out.println("-----------");
        System.out.println("Rows read       : " + extractor.getRowsRead());
        System.out.println("Rows transformed: " + transformed.size());
        System.out.println("Rows skipped    : " + extractor.getRowsSkipped());
        System.out.println("Output written to: " + OUTPUT);
    }
}

