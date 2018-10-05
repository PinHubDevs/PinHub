package dev.pinhub.pinhub.models;

public class DiscountedItem {
    private String name;
    private String description;
    // TODO: need to figure out the way to save images
    private Integer imageResourceId;
    private Double discountedPrice;
    private Integer discountPercentage;

    public DiscountedItem(String name, String description, Integer imageResourceId, Double discountedPrice, int discountPercentage) {
        this.name = name;
        this.description = description;
        this.imageResourceId = imageResourceId;
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

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(Integer imageResourceId) {
        this.imageResourceId = imageResourceId;
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
