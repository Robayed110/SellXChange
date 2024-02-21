package com.example.sellxchange.Domain;

import java.io.Serializable;

public class ProductDomain implements Serializable {
   String title,description,pic,price;
    private int numberInCart;

    public ProductDomain() {
    }

    public ProductDomain(String title, String description, String pic, String price, int numberInCart) {
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.price = price;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }


    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
