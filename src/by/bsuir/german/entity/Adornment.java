package by.bsuir.german.entity;

import by.bsuir.german.interfaces.ITitle;

import java.io.Serializable;
import java.util.List;

public class Adornment implements  Serializable, ITitle {

    private String title;
    private RingBase ring;
    private NecklaceBase necklace;
    private EarringBase earring;
    private Product product;
    private List<Stone> usedStones;

    public Adornment() {
    }

    public Adornment(String title, RingBase ring, List<Stone> usedStones) {
        this.title = title;
        this.ring = ring;
        this.usedStones = usedStones;
    }

    public Adornment(String title, NecklaceBase necklace, List<Stone> usedStones) {
        this.title = title;
        this.necklace = necklace;
        this.usedStones = usedStones;
    }

    public Adornment(String title, EarringBase earring, List<Stone> usedStones) {
        this.title = title;
        this.earring = earring;
        this.usedStones = usedStones;
    }

    public Adornment(String title, Product product, List<Stone> usedStones) {
        this.title = title;
        this.product = product;
        this.usedStones = usedStones;
    }

    public Product getProduct(){return product;}

    public String getTitle() {
        return title;
    }

    public RingBase getRing() {
        return ring;
    }

    public NecklaceBase getNecklace() {
        return necklace;
    }

    public EarringBase getEarring() {
        return earring;
    }

    public List<Stone> getUsedStones() {
        return usedStones;
    }
}
