package dev.pinhub.pinhub.storage.client.models;

public class ShopItem {
    private int id;
    private String name;
    private String address;
    private String imgUrl;
    private String description;
    private double distance;
    private Integer imageResourceId;
    private String type;
    private String discountImgUrl;

    public ShopItem(String name, String address, Integer imageResourceId, double distance) {
        this.name = name;
        this.address = address;
        this.imageResourceId = imageResourceId;
        this.distance = distance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(Integer imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDiscountImgUrl() {
        return discountImgUrl;
    }

    public void setDiscountImgUrl(String discountImgUrl) {
        this.discountImgUrl = discountImgUrl;
    }
}
