package com.sunbeaminfo.mobilestore.entity;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private int mid;
    private int uid;

    public Order(int id, int mid, int uid) {
        this.id = id;
        this.mid = mid;
        this.uid = uid;
    }

    public Order(int mid, int uid) {
        this.mid = mid;
        this.uid = uid;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
