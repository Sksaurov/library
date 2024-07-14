package com.example.library;

public class DataClass {
    private String dataName;
    private String dataPrice;
    private String image;

    public String getDataName() {
        return dataName;
    }

    public String getDataPrice() {
        return dataPrice;
    }

    public String getImage() {
        return image;
    }

    public DataClass(String dataName, String dataPrice, String image) {
        this.dataName = dataName;
        this.dataPrice = dataPrice;
        this.image = image;
    }
    public DataClass(){

    }
}
