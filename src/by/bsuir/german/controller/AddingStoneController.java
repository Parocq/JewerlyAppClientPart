package by.bsuir.german.controller;

//import com.gluonhq.charm.glisten.control.TextField;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.Stone;
import by.bsuir.german.entity.Storage;
import by.bsuir.german.entity.enumeration.StoneType;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddingStoneController {

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
    private RadioButton isPrecious;

    @FXML
    private RadioButton isHalfPrecious;

    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField colorField;

    @FXML
    private TextField weightField;

    @FXML
    private TextField transparenceField;

    @FXML
    private Button addStone;

    @FXML
    void ItemAddMetal(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/AddingMetal.fxml");
    }

    @FXML
    void ItemCreateAdornment(ActionEvent event) throws IOException {
        root.getScene().getWindow().hide();
        setScene("/by/bsuir/german/FXML/CreateAdornment.fxml");
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

    public String getFilePath() {
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
            serialization.serializeStorage(storageFull, filePath);
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
    void addStone(ActionEvent event) throws InputMismatchException {
        if (isPrecious.isSelected() || isHalfPrecious.isSelected()) {
            try {
                String title = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                double weight = Double.parseDouble(weightField.getText());
                double transparence = Double.parseDouble(transparenceField.getText());
                String color = colorField.getText();
                checkValues(weight, price, transparence);
                StoneType type = StoneType.Полудрагоценный;

                boolean chb = isPrecious.isSelected();
                if (chb) {
                    isHalfPrecious.setSelected(false);
                    type = StoneType.Драгоценный;
                }

                Stone stone = new Stone(title, weight, price, color, type, transparence);
                remoteClient.addStoneOnStock(stone);
                remoteClient.printMessageOnServer("Был добавлен камень! Название:"+title);
            } catch (InvalidFieldValueException e) {
                System.out.println("Ошибка вводимых значений!");
            } catch (NumberFormatException ex) {
                System.out.println("Ошибка форматов! / Не введены все значения!");
            }
        } else System.out.println("Необходимо отметить хотя бы 1 checkbox");
    }

    private void checkValues(double weight, double price, double transparence) throws InvalidFieldValueException {
        if (weight <= 0 || price < 0) {
            throw new InvalidFieldValueException();
        }
        if (transparence < 0 || transparence > 100) {
            throw new InvalidFieldValueException();
        }
    }

    @FXML
    void backToMenu(ActionEvent event) throws IOException {
        addStone.getScene().getWindow().hide();
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
    }
}
