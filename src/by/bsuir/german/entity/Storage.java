package by.bsuir.german.entity;

import by.bsuir.german.entity.tabled.AdornmentExtended;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {

    private List<Stone> stones;// = new ArrayList<>();
    private List<Metal> metals;// = new ArrayList<>();
    private List<Adornment> adornments;// = new ArrayList<>();
    private List<RingBase> ringBases;// = new ArrayList<>();
    private List<NecklaceBase> necklaceBases;// = new ArrayList<>();
    private List<EarringBase> earringBases;// = new ArrayList<>();

    private ObservableList<AdornmentExtended> adornmentExtendedList;

    public Storage(List<Stone> stones, List<Metal> metals, List<Adornment> adornments,
                   List<RingBase> ringBases, List<NecklaceBase> necklaceBases, List<EarringBase> earringBases) {
        this.stones = stones;
        this.metals = metals;
        this.adornments = adornments;
        this.ringBases = ringBases;
        this.necklaceBases = necklaceBases;
        this.earringBases = earringBases;
    }

    public Storage() {
        this.stones = new ArrayList<>();
        this.metals = new ArrayList<>();
        this.adornments = new ArrayList<>();
        this.ringBases = new ArrayList<>();
        this.necklaceBases = new ArrayList<>();
        this.earringBases = new ArrayList<>();
        this.adornmentExtendedList = FXCollections.observableArrayList();
    }

    public ObservableList<AdornmentExtended> getAdornmentExtendedList () {return  adornmentExtendedList;}

    public List<Stone> getStones() {
        return stones;
    }

    public List<Metal> getMetals() {
        return metals;
    }

    public List<Adornment> getAdornments() {
        return adornments;
    }

    public List<RingBase> getRingBases() {
        return ringBases;
    }

    public List<NecklaceBase> getNecklaceBases() {
        return necklaceBases;
    }

    public List<EarringBase> getEarringBases() {
        return earringBases;
    }
}
