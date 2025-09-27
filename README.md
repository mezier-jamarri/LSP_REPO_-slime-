## Assignment 2 - ETL Pipeline

## Assumptions
- Input CSV file is named `products.csv` and is located in the `data/` folder.
- Output file is always written to `data/transformed_products.csv`.
- The program must be run from the **project root** so relative paths resolve correctly.
- If the input file is missing, the program prints an error and exits.  
- If the input file only has a header (empty input), the program still writes an output file with just the header.

## Design Notes
- Extract → Transform → Load design:
  1. **Extract**: Reads `products.csv` with a simple CSV parser using `BufferedReader`.
  2. **Transform**:
     - Converts product names to uppercase.
     - Applies 10% discount to Electronics, rounding to 2 decimals with `RoundingMode.HALF_UP`.
     - If discounted price > 500.00 in Electronics → category changed to *Premium Electronics*.
     - Computes a `PriceRange` from the final price:
       - 0–10 → Low  
       - 10.01–100 → Medium  
       - 100.01–500 → High  
       - 500.01+ → Premium
  3. **Load**: Writes `transformed_products.csv` using `BufferedWriter` with header row included.

## AI Usage
I used AI to clarify project structure, relative paths, and CSV handling in Java.
	•	AI provided code examples for reading/writing CSVs with BufferedReader/BufferedWriter.
I modified the code to:
	•	Add a helper method for rounding with BigDecimal.
	•	Ensure the header row was skipped properly during extraction.
	•	Print summary counts after execution for easier debugging.

