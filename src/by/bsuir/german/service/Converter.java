package by.bsuir.german.service;

import by.bsuir.german.entity.Adornment;
import by.bsuir.german.entity.Metal;
import by.bsuir.german.entity.Stone;
import by.bsuir.german.entity.tabled.AdornmentExtended;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Converter {

    private RemoteClient remoteClient;

    public Converter() {
    }

    public Converter(RemoteClient remoteClient) {
        this.remoteClient = remoteClient;
    }

    public ObservableList<AdornmentExtended> convertArrayListToObservableListA(List<Adornment> adornments) {
        ObservableList<AdornmentExtended> observableList = FXCollections.observableArrayList();
        for (Adornment a : adornments) {
            AdornmentExtended adornmentExtended = new AdornmentExtended(a,remoteClient);
            observableList.add(adornmentExtended);
        }
        return observableList;
    }

    public ObservableList<Stone> convertArrayListToObservableListS(List<Stone> stones) {
        ObservableList<Stone> observableList = FXCollections.observableArrayList();
        observableList.addAll(stones);
        return observableList;
    }

    public ObservableList<Metal> convertArrayListToObservableListM(List<Metal> metals) {
        ObservableList<Metal> observableList = FXCollections.observableArrayList();
        observableList.addAll(metals);
        return observableList;
    }
}
