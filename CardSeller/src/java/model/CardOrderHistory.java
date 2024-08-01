/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author hacom
 */
public class CardOrderHistory {
    private String providerName, image;
    private int price, id, quantity;
    private LocalDateTime buyDate;

    public CardOrderHistory(String providerName,
            int price, int id,
            int quantity, LocalDateTime buyDate,
            String image) {
        this.providerName = providerName;
        this.price = price;
        this.id = id;
        this.quantity = quantity;
        this.buyDate = buyDate;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDateTime buyDate) {
        this.buyDate = buyDate;
    }

    @Override
    public String toString() {
        return "CardOrderHistory{" + "providerName=" + providerName + ", image=" + image + ", price=" + price + ", id=" + id + ", quantity=" + quantity + ", buyDate=" + buyDate + '}';
    }
}
