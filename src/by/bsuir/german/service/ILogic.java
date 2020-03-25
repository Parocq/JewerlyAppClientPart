package by.bsuir.german.service;

import by.bsuir.german.entity.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ILogic extends Remote {

    void fillStorage(Storage st) throws RemoteException;

    void sortMetalByTitle(List<Metal> list) throws RemoteException;

    void sortStonesByTitle(List<Stone> list) throws RemoteException;

    void sortRingBaseByTitle(List<RingBase> list) throws RemoteException;

    void sortEarringBaseByTitle(List<EarringBase> list) throws RemoteException;

    void sortNecklaceBaseByTitle(List<NecklaceBase> list) throws RemoteException;

    void sortAdornmentByTitle(List<Adornment> list) throws RemoteException;

    void sortStonesByPrice(List<Stone> list) throws RemoteException;

    double calculateWeight(Adornment adornment) throws RemoteException;

    double calculatePrice(Adornment adornment) throws RemoteException;

    List<Stone> searchForTransparence(double start, double finish) throws RemoteException;

}
