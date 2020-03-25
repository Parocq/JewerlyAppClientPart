package by.bsuir.german.entity;

import java.io.Serializable;

public class RingBase extends Product implements Serializable {
    private double diametr;

    public RingBase(String title, double weight, double price, Metal metal, double diametr, String type) {
        super(title, weight, price, metal,type);
        this.diametr = diametr;
    }

    public double getDiametr() {
        return diametr;
    }
}
