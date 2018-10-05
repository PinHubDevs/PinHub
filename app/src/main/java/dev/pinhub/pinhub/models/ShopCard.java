package dev.pinhub.pinhub.models;

public class ShopCard {

    private String name;
    private String address;
    private String imgUrl;
    private double distance;

    public ShopCard(String name, String address, String imgUrl, double distance) {
        this.name = name;
        this.address = address;
        this.imgUrl = imgUrl;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
