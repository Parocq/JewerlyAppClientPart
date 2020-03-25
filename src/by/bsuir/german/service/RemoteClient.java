package by.bsuir.german.service;

import by.bsuir.german.entity.*;
import by.bsuir.german.entity.tabled.AdornmentExtended;
import by.bsuir.german.interfaces.ITitle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RemoteClient implements IRemoteServer {

    private IRemoteServer myReestr = null;

    public RemoteClient() {
        try {
            Registry registry = LocateRegistry.getRegistry("IP of host");
            myReestr = (IRemoteServer) registry.lookup("String");
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void fillStorage(Storage st) {
        try {
            myReestr.fillStorage(st);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortMetalByTitle(List<Metal> list) {
        try {
            myReestr.sortMetalByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortStonesByTitle(List<Stone> list) {
        try {
            myReestr.sortStonesByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortRingBaseByTitle(List<RingBase> list) {
        try {
            myReestr.sortRingBaseByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortEarringBaseByTitle(List<EarringBase> list) {
        try {
            myReestr.sortEarringBaseByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortNecklaceBaseByTitle(List<NecklaceBase> list) {
        try {
            myReestr.sortNecklaceBaseByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortAdornmentByTitle(List<Adornment> list) {
        try {
            myReestr.sortAdornmentByTitle(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sortStonesByPrice(List<Stone> list) {
        try {
            myReestr.sortStonesByPrice(list);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calculateWeight(Adornment adornment) {
        double finalWeight = 0;
        try {
            finalWeight = myReestr.calculateWeight(adornment);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return finalWeight;
    }

    @Override
    public double calculatePrice(Adornment adornment) {
        double finalPrice = 0;
        try {
            finalPrice = myReestr.calculatePrice(adornment);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return finalPrice;
    }

    @Override
    public List<Stone> searchForTransparence(double start, double finish) {
        List<Stone> list = new ArrayList<>();
        try {
            myReestr.searchForTransparence(start,finish);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public ObservableList<Stone> convertArrayListToObservableListS() {
        ObservableList<Stone> observableList = FXCollections.observableArrayList();
        try {
            myReestr.convertArrayListToObservableListS();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return  observableList;
    }

    @Override
    public ObservableList<Metal> convertArrayListToObservableListM() {
        ObservableList<Metal> observableList = FXCollections.observableArrayList();
        try {
            myReestr.convertArrayListToObservableListM();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    @Override
    public void fillAdornmentObservableList() {
        try {
            myReestr.fillAdornmentObservableList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<AdornmentExtended> getAdornmentExtendedList() {
        ObservableList<AdornmentExtended> observableList = FXCollections.observableArrayList();
        try {
            myReestr.getAdornmentExtendedList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return observableList;
    }

    @Override
    public void addAdormentExtendedOnStock(AdornmentExtended adornmentExtended) {
        try {
            myReestr.addAdormentExtendedOnStock(adornmentExtended);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Stone> getStones() {
        List<Stone> list = new ArrayList<>();
        try {
            myReestr.getStones();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Metal> getMetals() {
        List<Metal> list = new ArrayList<>();
        try {
            myReestr.getMetals();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Adornment> getAdornments() {
        List<Adornment> list = new ArrayList<>();
        try {
            myReestr.getAdornments();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<RingBase> getRingBases() {
        List<RingBase> list = new ArrayList<>();
        try {
            myReestr.getRingBases();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<NecklaceBase> getNecklaceBases() {
        List<NecklaceBase> list = new ArrayList<>();
        try {
            myReestr.getNecklaceBases();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<EarringBase> getEarringBases() {
        List<EarringBase> list = new ArrayList<>();
        try {
            myReestr.getEarringBases();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addRingBaseOnStock(RingBase ringBase) {
        try {
            myReestr.addRingBaseOnStock(ringBase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addNecklaceBaseOnStock(NecklaceBase necklaceBase) {
        try {
            myReestr.addNecklaceBaseOnStock(necklaceBase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addEarringBaseOnStock(EarringBase earringBase) {
        try {
            myReestr.addEarringBaseOnStock(earringBase);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addStoneOnStock(Stone stone) {
        try {
            myReestr.addStoneOnStock(stone);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addMetalOnStock(Metal metal) {
        try {
            myReestr.addMetalOnStock(metal);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addAdornmentOnStock(Adornment adornment) {
        try {
            myReestr.addAdornmentOnStock(adornment);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getTitles(List<? extends ITitle> objects) {
        String s = null;
        try {
            myReestr.getTitles(objects);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return s;
    }

    @Override
    public void printAll() {
        try {
            myReestr.printAll();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAdormentTitles() {
        String s = null;
        try {
            myReestr.getAdormentTitles();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return s;
    }
}
