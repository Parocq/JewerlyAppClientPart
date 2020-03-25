package by.bsuir.german;

import by.bsuir.german.service.Converter;
import by.bsuir.german.service.RemoteClient;
import by.bsuir.german.service.Serialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.*;
import java.net.Inet4Address;
import java.rmi.RemoteException;
import java.util.InputMismatchException;


public class MainFX extends Application {

    private static Serialization serialization;
    private static RemoteClient remoteClient;
    private static Converter converter;

    @Override
    public void start(Stage myStage) {
        try {
            remoteClient = new RemoteClient();
            serialization = new Serialization();
            converter = new Converter(remoteClient);

            Parent root = FXMLLoader.load(getClass().getResource("FXML/NewMainScreen.fxml"));

            Scene scene = new Scene(root);
            myStage.setScene(scene);
            myStage.setTitle("Jewelry shop");
            System.out.println("START");
            myStage.show();

        }catch (RemoteException e ){
            System.out.println("wtf");
        }  catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка при вводе данных! Проверьте вводимые типы!");
        }
    }

    @Override
    public void stop() throws Exception {
        remoteClient.printMessageOnServer("\u001B[34m"+"\nПользователь с IPv4:"+
                Inet4Address.getLocalHost().getHostAddress()+" вышел из системы.\n"+"\u001B[0m");
    }

    public static void main(String[] args) {
        launch(args);
    }

    public RemoteClient getRemoteClient(){return remoteClient;}

    public Serialization getSerialization() {
        return serialization;
    }

    public Converter getConverter(){ return converter; }

//    Серверная часть и клиентская часть. На клиентской части этот интерфейс и классы сопутствующие.Все что с интерфесом - оставлем ра клиенте.
//        А на сервере файлы хранятся. Каждый клиент в отдельном потоке работает для сервера.
//        При сэйве, даннные которы ена клиете изменились - отправляются на сервер
//
//    На сервере выводится инфа только о том кто подключился\отключился ( по возможности сохранение)
//
//    Клиент-серерное взаимедействие
//            Многопоточность
}