package by.bsuir.german.entity.tabled;

import by.bsuir.german.entity.Adornment;
import by.bsuir.german.service.RemoteClient;

import java.io.Serializable;

public class AdornmentExtended implements Serializable {
    private String title;
    private String type;
    private double price;
    private double weight;
    private String baseTitle;
    private String usedStones;

    RemoteClient remoteClient;

    public AdornmentExtended() {
    }

    public AdornmentExtended(Adornment adornment, RemoteClient remoteClient) {
        this.remoteClient = remoteClient;
        this.title = adornment.getTitle();

        if (adornment.getEarring() != null) {
            this.type = adornment.getEarring().getType();
        } else if (adornment.getNecklace() != null) {
            this.type = adornment.getNecklace().getType();
        } else this.type = adornment.getRing().getType();

        this.price = remoteClient.calculatePrice(adornment);
        this.weight = remoteClient.calculateWeight(adornment);

        if (adornment.getEarring() != null) {
            this.baseTitle = adornment.getEarring().getTitle();
        } else if (adornment.getNecklace() != null) {
            this.baseTitle = adornment.getNecklace().getTitle();
        } else this.baseTitle = adornment.getRing().getTitle();

        this.usedStones = remoteClient.getTitles(adornment.getUsedStones());
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public double getWeight() {
        return weight;
    }

    public String getBaseTitle() {
        return baseTitle;
    }

    public String getUsedStones() {
        return usedStones;
    }
}

