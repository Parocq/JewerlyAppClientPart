package by.bsuir.german.entity;

import by.bsuir.german.interfaces.ITitle;

import java.io.Serializable;

public abstract class Material  implements Serializable, ITitle {
    private String title;
    private double weight;
    private double price;

    public Material(String title, double weight, double price) {
        this.title = title;
        this.weight = weight;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public double getWeight() {
        return weight;
    }

    public double getPrice() {
        return price;
    }
}
