package com.example.sellxchange.Domain;

import java.io.Serializable;

public class PopularDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double price;

    private int numberInCart;

    public PopularDomain(String title, String pic, String description, Double price) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;

    }

    public PopularDomain(String title, String pic, String description, Double price, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.price = price;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
