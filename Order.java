package com.example.library;

public class Order {
    private String itemName;
    private String itemPrice;
    private String phoneNumber;
    private String address;

    public Order() {

    }

    public Order(String itemName, String itemPrice, String phoneNumber, String address) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
