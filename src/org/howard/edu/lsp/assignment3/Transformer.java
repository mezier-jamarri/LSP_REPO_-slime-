package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
/**
 * Transform step: applies all Assignment 2 transformations in the required order.
 * <ol>
 *   <li>Uppercase name</li>
 *   <li>10% discount if original category is "Electronics" (round HALF_UP to 2 decimals)</li>
 *   <li>If post-discount price &gt; 500.00 and original category was "Electronics",
 *       set category to "Premium Electronics"</li>
 *   <li>Compute final {@code PriceRange} from final price</li>
 * </ol>
 */

public class Transformer {
/**
     * Applies the transform rules (in order) to the provided list of products.
     *
     * @param input list of products extracted from CSV
     * @return a new list containing transformed products
     */
    public List<Product> transform(List<Product> input) {
        List<Product> out = new ArrayList<>();
        for (Product p : input) {
            // 1) uppercase
            p.setName(p.getName() == null ? "" : p.getName().toUpperCase());

            // 2) discount if original category is Electronics
            if ("Electronics".equalsIgnoreCase(p.getOriginalCategory())) {
                BigDecimal discounted = p.getPrice().multiply(new BigDecimal("0.90"));
                p.setPrice(round2(discounted));
            } else {
                p.setPrice(round2(p.getPrice()));
            }

            // 3) possible recategorization
            if ("Electronics".equalsIgnoreCase(p.getOriginalCategory())
                    && p.getPrice().compareTo(new BigDecimal("500.00")) > 0) {
                p.setCategory("Premium Electronics");
            }

            // 4) price range from final price
            p.setPriceRange(priceRange(p.getPrice()));

            out.add(p);
        }
        return out;
    }

    /** Rounds a BigDecimal to 2 decimals using HALF_UP (banker's friendly). */
    private static BigDecimal round2(BigDecimal v) {
        return v.setScale(2, RoundingMode.HALF_UP);
    }

    /** Computes price range label from final price with inclusive/exclusive bounds per spec. */
    private static String priceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("0.00")) >= 0
                && price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("10.00")) > 0
                && price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("100.00")) > 0
                && price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}

