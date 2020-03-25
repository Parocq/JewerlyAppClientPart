package by.bsuir.german.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.EarringBase;
import by.bsuir.german.entity.Metal;
import by.bsuir.german.entity.Storage;
import by.bsuir.german.exception.InvalidFieldValueException;
import by.bsuir.german.service.RemoteClient;
import by.bsuir.german.service.Serialization;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateEarringBaseController {

    private RemoteClient remoteClient;
    private MainFX mainFX;
    private Serialization serialization;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField weightField;

    @FXML
    private Button addEarringBase;

    @FXML
    private Button back;

    @FXML
    private TextArea metalListField;

    @FXML
    private TextField choosenMetal;

    @FXML
    private CheckBox isPair;

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
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/StorageContent.fxml");
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
    void AddEarringBase(ActionEvent event) throws NumberFormatException {
        try {
            String title = nameField.getText();
            double price = Double.parseDouble(priceField.getText());
            double weight = Double.parseDouble(weightField.getText());
            boolean paired = isPair.isSelected();
            int metallNum = Integer.parseInt(choosenMetal.getText()) - 1;

            checkValues(price, weight, metallNum);

            Metal metal = remoteClient.getMetals().get(metallNum);

            EarringBase earringBase = new EarringBase(title, weight, price, metal, paired,"Серьги");
            remoteClient.addEarringBaseOnStock(earringBase);
        } catch (InvalidFieldValueException e) {
            System.out.println("Ошибка вводимых значений!");
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка форматов! / Не введены все значения!");
        }
    }

    private void checkValues(double price, double weight, int metal) throws InvalidFieldValueException {
        if (weight <= 0 || price < 0) {
            throw new InvalidFieldValueException();
        }
        if (metal > remoteClient.getMetals().size() - 1 || metal < 0) {
            throw new InvalidFieldValueException();
        }
    }

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        back.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("/by/bsuir/german/FXML/NewMainScreen.fxml"));
        Scene scene = new Scene(root);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(scene);
        window.show();
    }

    @FXML
    void initialize() {
        mainFX = new MainFX();
        remoteClient = mainFX.getRemoteClient();
        serialization = mainFX.getSerialization();
        metalListField.setText(remoteClient.getTitles(remoteClient.getMetals()));
    }
}
