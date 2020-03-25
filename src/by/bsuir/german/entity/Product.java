package by.bsuir.german.entity;

import by.bsuir.german.interfaces.ITitle;

import java.io.Serializable;

public abstract class Product implements Serializable, ITitle {
    private String title;
    private double weight;
    private double price;
    private Metal metal;
    private String type;


    public Product(String title, double weight, double price, Metal metal, String type) {
        this.title = title;
        this.weight = weight;
        this.price = price;
        this.metal = metal;
        this.type = type;
    }

    public String getType(){return  type;}

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }
}
