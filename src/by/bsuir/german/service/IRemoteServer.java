package by.bsuir.german.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRemoteServer extends ILogic,IStorageWorker, Remote {

    void printMessageOnServer(String s) throws RemoteException;
}
