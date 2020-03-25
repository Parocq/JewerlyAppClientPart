package by.bsuir.german.controller;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.Metal;
import by.bsuir.german.entity.Storage;
import by.bsuir.german.entity.tabled.AdornmentExtended;
import by.bsuir.german.service.Converter;
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


public class MetalsTableController {

    private RemoteClient remoteClient;
    private MainFX mainFX;
    private Serialization serialization;
    private Converter converter;

    private ObservableList<Metal> observableList;

    @FXML
    private Label banner;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Metal> tableMetals;

    @FXML
    private TableColumn<Metal, String> idTitle;

    @FXML
    private TableColumn<Metal, Double> idPrice;

    @FXML
    private TableColumn<Metal, Double> idWeight;

    @FXML
    private TableColumn<Metal, Double> idSample;

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


    public void goToStones(ActionEvent event) {
        banner.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/StonesTable.fxml");
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
        tableMetals.getItems().clear();

        List<Metal> list = remoteClient.getMetals();
        observableList = converter.convertArrayListToObservableListM(list);

        idTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        idPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        idWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        idSample.setCellValueFactory(new PropertyValueFactory<>("sample"));

        tableMetals.setItems(observableList);
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
        converter = mainFX.getConverter();
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
