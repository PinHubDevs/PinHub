package dev.pinhub.pinhub.models;

import java.math.BigDecimal;

public class DiscountedItem {
    private String name;
    private String description;
    private String imageUrl; // How do we save images?
    private BigDecimal priceBeforeDiscount;
    private int discountPercentage;
}
