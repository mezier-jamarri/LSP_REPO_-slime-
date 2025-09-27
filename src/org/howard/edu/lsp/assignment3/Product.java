package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Domain model representing a single product row flowing through the ETL pipeline.
 * <p>
 * The object stores both the current, transformed values (name, price, category)
 * and the original category so that later transform steps (e.g., recategorization)
 * can make decisions based on the pre-transform category.
 */

public class Product {
    private final int id;
    private String name;
    private BigDecimal price;
    private String category;
    private final String originalCategory; // capture on construction
    private String priceRange;             // derived during transform, used at load

    /**
     * Constructs a Product.
     *
     * @param id       product identifier
     * @param name     product name (will be further transformed)
     * @param price    product price (currency, two-decimal rounding applied in transforms)
     * @param category product category (original category is captured for later rules)
     */
    public Product(int id, String name, BigDecimal price, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.originalCategory = category;
    }

    /** @return the product id */
    public int getId() { return id; }

    /** @return the (possibly transformed) name */
    public String getName() { return name; }

    /** Sets the (possibly transformed) name. */
    public void setName(String name) { this.name = name; }

    /** @return the (possibly transformed) price */
    public BigDecimal getPrice() { return price; }

    /** Sets the (possibly transformed) price. */
    public void setPrice(BigDecimal price) { this.price = price; }

    /** @return the (possibly transformed) category */
    public String getCategory() { return category; }

    /** Sets the (possibly transformed) category. */
    public void setCategory(String category) { this.category = category; }

    /** @return the original (pre-transform) category */
    public String getOriginalCategory() { return originalCategory; }

    /** @return the derived price range label (set during transform) */
    public String getPriceRange() { return priceRange; }

    /** Sets the derived price range label. */
    public void setPriceRange(String priceRange) { this.priceRange = priceRange; }
}

