package by.bsuir.german.entity;

import by.bsuir.german.entity.enumeration.StoneType;

import java.io.Serializable;

public class Stone extends Material implements Serializable {

    private String color;
    private StoneType stoneType;
    private double transparence;

    public Stone(String title, double weight, double price, String color, StoneType stoneType, double transparence) {
        super(title, weight, price);
        this.color = color;
        this.stoneType = stoneType;
        this.transparence = transparence;
    }

    public double getTransparence() {
        return transparence;
    }

    public String getColor() {
        return color;
    }

    public StoneType getStoneType() {
        return stoneType;
    }
}
