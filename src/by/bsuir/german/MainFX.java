package by.bsuir.german;

import by.bsuir.german.entity.*;
import by.bsuir.german.entity.enumeration.StoneType;
import by.bsuir.german.entity.tabled.AdornmentExtended;
import by.bsuir.german.service.RemoteClient;
import by.bsuir.german.service.Serialization;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class MainFX extends Application {

//    private static Storage storage;
//    private static Logic logic;
    private static Serialization serialization;
//    private AdornmentExtended adornmentExtended;
    private RemoteClient remoteClient;

    @Override
    public void start(Stage myStage) throws Exception {
        try {
            remoteClient = new RemoteClient();
//            MainFX mainFX = new MainFX();
//            storage = new Storage();
//            logic = new Logic(storage);
//            storage = mainFX.getStorage();
            serialization = new Serialization();
//            adornmentExtended = new AdornmentExtended(logic,storage);
//
//            Stone stone = new Stone("Камень",22.3,12.3,"Красный", StoneType.Драгоценный,11.2);
//            storage.addStoneOnStock(stone);
//            List<Stone> stoneList = new ArrayList<>();
//            stoneList.add(stone);
//            Metal metal = new Metal("Металл",323.23,232.1,2.3);
//            storage.addMetalOnStock(metal);
//            RingBase ringBase =  new RingBase("Кольца основа",32,45, metal,32.3,"Кольцо");
//            storage.addRingBaseOnStock(ringBase);
//
//            Adornment adornment = new Adornment("Украшение", ringBase,stoneList);
//            storage.addAdornmentOnStock(adornment);

            Parent root = FXMLLoader.load(getClass().getResource("FXML/NewMainScreen.fxml"));

            Scene scene = new Scene(root);
            myStage.setScene(scene);
            myStage.setTitle("Jewelry shop");
            System.out.println("START");
            myStage.show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("Ошибка при вводе данных! Проверьте вводимые типы!");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    public RemoteClient getRemoteClient(){return remoteClient;}

//    public Storage getStorage() {
//        return storage;
//    }
//
//    public Logic getLogic() {
//        return logic;
//    }
//
    public Serialization getSerialization() {
        return serialization;
    }

//    Серверная часть и клиентская часть. На клиентской части этот интерфейс и классы сопутствующие.Все что с интерфесом - оставлем ра клиенте.
//        А на сервере файлы хранятся. Каждый клиент в отдельном потоке работает для сервера.
//        При сэйве, даннные которы ена клиете изменились - отправляются на сервер
//
//    На сервере выводится инфа только о том кто подключился\отключился ( по возможности сохранение)
//
//    Клиент-серерное взаимедействие
//            Многопоточность
}