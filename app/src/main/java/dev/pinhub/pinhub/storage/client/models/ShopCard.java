package dev.pinhub.pinhub.storage.client.models;

public class ShopCard {

    private String name;
    private String address;
    private Integer imageResourceId;
    private double distance;

    public ShopCard(String name, String address, Integer imageResourceId, double distance) {
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
}
