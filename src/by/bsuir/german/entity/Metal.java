package by.bsuir.german.entity;

import java.io.Serializable;

public class Metal extends Material implements Serializable {

    private double sample;

    public Metal(String title, double weight, double price, double sample) {
        super(title, weight, price);
        this.sample = sample;
    }

    public double getSample() {
        return sample;
    }
}
