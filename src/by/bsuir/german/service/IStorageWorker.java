package by.bsuir.german.service;

import by.bsuir.german.entity.*;
import by.bsuir.german.entity.tabled.AdornmentExtended;
import by.bsuir.german.interfaces.ITitle;
import javafx.collections.ObservableList;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface IStorageWorker extends Remote {

    void fillAdornmentObservableList() throws RemoteException;

    ObservableList<AdornmentExtended> getAdornmentExtendedList() throws RemoteException;

    void addAdormentExtendedOnStock (AdornmentExtended adornmentExtended) throws RemoteException;

    List<Stone> getStones() throws RemoteException;

    List<Metal> getMetals() throws RemoteException;

    List<Adornment> getAdornments() throws RemoteException;

    List<RingBase> getRingBases() throws RemoteException;

    List<NecklaceBase> getNecklaceBases() throws RemoteException;

    List<EarringBase> getEarringBases() throws RemoteException;

    void addRingBaseOnStock(RingBase ringBase) throws RemoteException;

    void addNecklaceBaseOnStock(NecklaceBase necklaceBase) throws RemoteException;

    void addEarringBaseOnStock(EarringBase earringBase) throws RemoteException;

    void addStoneOnStock(Stone stone) throws RemoteException;

    void addMetalOnStock(Metal metal) throws RemoteException;

    void addAdornmentOnStock(Adornment adornment) throws RemoteException;

    String getTitles(List<? extends ITitle> objects) throws RemoteException;

    void printAll() throws RemoteException;

    String getAdormentTitles() throws RemoteException;
}
