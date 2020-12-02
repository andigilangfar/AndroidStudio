package com.example.tugas4.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pulsa {
    private int id;
    private String code;
    private double nominal;
    private double price;

    public Pulsa(){
    }

    public Pulsa(String code, double nominal){
        this.nominal = nominal;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getNominal() {
        return nominal;
    }

    public void setNominal(double nominal) {
        this.nominal = nominal;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
