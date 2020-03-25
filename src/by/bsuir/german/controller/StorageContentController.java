package by.bsuir.german.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.Storage;
import by.bsuir.german.service.RemoteClient;
import by.bsuir.german.service.Serialization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StorageContentController {

    private RemoteClient remoteClient;
    private MainFX mainFX;
    private Serialization serialization;

    @FXML
    public Button sortButton;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private Button back;

    @FXML
    private TextArea stonesList;

    @FXML
    private TextArea metalsList;

    @FXML
    private TextArea earringBasesList;

    @FXML
    private TextArea necklaceBasesList;

    @FXML
    private TextArea ringBasesList;

    @FXML
    private TextArea adornmentList;

    @FXML
    private Button update;

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

    public void goToStones(ActionEvent event) {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/StonesTable.fxml");
    }

    public void goToMetals(ActionEvent event) {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/MetalsTable.fxml");
    }

    public String getFilePath (){
        final FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
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

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        update.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/by/bsuir/german/FXML/NewMainScreen.fxml"));
        Scene scene = new Scene(root);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void update(ActionEvent event) {
        setLists();
    }

    private void setLists (){
        stonesList.setText(remoteClient.getTitles(remoteClient.getStones()));
        metalsList.setText(remoteClient.getTitles(remoteClient.getMetals()));
        earringBasesList.setText(remoteClient.getTitles(remoteClient.getEarringBases()));
        necklaceBasesList.setText(remoteClient.getTitles(remoteClient.getNecklaceBases()));
        ringBasesList.setText(remoteClient.getTitles(remoteClient.getRingBases()));
        adornmentList.setText(remoteClient.getTitles(remoteClient.getAdornments()));
    }

    public void sortAll(ActionEvent actionEvent) {
        remoteClient.sortAdornmentByTitle(remoteClient.getAdornments());
        remoteClient.sortRingBaseByTitle(remoteClient.getRingBases());
        remoteClient.sortNecklaceBaseByTitle(remoteClient.getNecklaceBases());
        remoteClient.sortEarringBaseByTitle(remoteClient.getEarringBases());
        remoteClient.sortStonesByTitle(remoteClient.getStones());
        remoteClient.sortMetalByTitle(remoteClient.getMetals());
    }

    @FXML
    void initialize() {
        mainFX = new MainFX();
        remoteClient = mainFX.getRemoteClient();
        serialization = mainFX.getSerialization();
        setLists();
    }
}
