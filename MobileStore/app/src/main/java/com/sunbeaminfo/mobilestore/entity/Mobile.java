package com.sunbeaminfo.mobilestore.entity;

import java.io.Serializable;

public class Mobile implements Serializable {
    private int id;
    private String name;
    private int ram;
    private int storage;
    private double price;
    private String company;
    private String image;

    public Mobile(){

    }

    public Mobile(int id, String name, int ram, int storage, double price, String company, String image) {
        this.id = id;
        this.name = name;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
        this.company = company;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
