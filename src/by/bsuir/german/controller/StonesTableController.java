package by.bsuir.german.controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.Stone;
import by.bsuir.german.entity.Storage;
import by.bsuir.german.service.RemoteClient;
import by.bsuir.german.service.Serialization;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class StonesTableController {

    //    private IO io;
    private RemoteClient remoteClient;
    private MainFX mainFX;
    private Serialization serialization;

    private ObservableList<Stone> stonesExtendedList;

    @FXML
    private Label banner;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Stone> tableAdornments;

    @FXML
    private TableColumn<Stone, String> idTitle;

    @FXML
    private TableColumn<Stone, String> idType;

    @FXML
    private TableColumn<Stone, Double> idPrice;

    @FXML
    private TableColumn<Stone, Double> idWeight;

    @FXML
    private TableColumn<Stone, String> idColor;

    @FXML
    private TableColumn<Stone, Double> idTransparence;

    @FXML
    void ItemAddMetal(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene( "/by/bsuir/german/FXML/AddingMetal.fxml");
    }

    @FXML
    void ItemAddStone(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/AddingStone.fxml");
    }

    @FXML
    void ItemCreateAdornment(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene( "/by/bsuir/german/FXML/CreateAdornment.fxml");
    }

    @FXML
    void ItemCreateEarring(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/CreateEarringBase.fxml");
    }

    @FXML
    void ItemCreateNecklace(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/CreateNecklaceBase.fxml");
    }

    @FXML
    void ItemCreateRing(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/CreateRingBase.fxml");
    }

    @FXML
    void goToStorage(ActionEvent event) throws IOException {
        banner.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/StorageContent.fxml");
    }

    public void goToMetals(ActionEvent event) {
        banner.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/MetalsTable.fxml");
    }

    public void goMain(ActionEvent event) throws IOException {
        banner.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/NewMainScreen.fxml");
    }

    public String getFilePath (){
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) banner.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);
        return file.getAbsolutePath();
    }

    @FXML
    void openFile(ActionEvent event) {
        try {
            String filePath = getFilePath();
            remoteClient.fillStorage(serialization.desirealizeStorage(filePath));
            System.out.println("Успех!");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        } catch (NullPointerException e) {
            System.out.println("Файл пуст! Нечего десериализоввывать.");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setTableValues();
    }


    @FXML
    void saveFile(ActionEvent event) {
        try {
            Storage storageFull = new Storage(remoteClient.getStones(), remoteClient.getMetals(), remoteClient.getAdornments(),
                    remoteClient.getRingBases(), remoteClient.getNecklaceBases(), remoteClient.getEarringBases());
            String filePath = getFilePath();
            serialization.serializeStorage(storageFull,filePath);
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        } catch (NullPointerException e) {
            System.out.println("Список пуст! Нечего сериализоввывать.");
        }
    }

    @FXML
    void update(ActionEvent event) {
        setTableValues();
    }

    public void setTableValues (){
        tableAdornments.getItems().clear();

        stonesExtendedList = remoteClient.convertArrayListToObservableListS();
        idTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        idType.setCellValueFactory(new PropertyValueFactory<>("stoneType"));
        idPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        idWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        idColor.setCellValueFactory(new PropertyValueFactory<>("color"));
        idTransparence.setCellValueFactory(new PropertyValueFactory<>("transparence"));

        tableAdornments.setItems(stonesExtendedList);
        idTitle.getColumns().clear();
    }

    @FXML
    void initialize() {
        mainFX = new MainFX();
        initializateVariables();

        setTableValues();
        setTableValues();
    }

    private void initializateVariables() {
        remoteClient = mainFX.getRemoteClient();
        serialization = mainFX.getSerialization();
    }

    private void setScene(String fileLocation) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(fileLocation));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root2 = fxmlLoader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root2));
        stage.show();
    }
}
