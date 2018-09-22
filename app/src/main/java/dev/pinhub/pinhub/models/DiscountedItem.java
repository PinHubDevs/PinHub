package dev.pinhub.pinhub.models;

import java.math.BigDecimal;

public class DiscountedItem {
    private String name;
    private String description;
    private String imageUrl; // How do we save images?
    private BigDecimal priceBeforeDiscount;
    private int discountPercentage;

    public DiscountedItem(String name, String description, String imageUrl, BigDecimal priceBeforeDiscount, int discountPercentage) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.priceBeforeDiscount = priceBeforeDiscount;
        this.discountPercentage = discountPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(BigDecimal priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
