package dev.pinhub.pinhub.models;

public class DiscountedItem {
    private String name;
    private String description;
    // TODO: need to figure out the way to save images
    private String imageUrl;
    private Double discountedPrice;
    private Integer discountPercentage;

    public DiscountedItem(String name, String description, String imageUrl, Double discountedPrice, int discountPercentage) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.discountedPrice = discountedPrice;
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

    public Double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
