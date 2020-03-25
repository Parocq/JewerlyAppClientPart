package by.bsuir.german.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import by.bsuir.german.MainFX;
import by.bsuir.german.entity.*;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class CreateAdornmentController {

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
    private Button addEarringBase;

    @FXML
    private Button back;

    @FXML
    private TextArea stonesListField;

    @FXML
    private TextArea choosedStones;

    @FXML
    private Button getVariants;

    @FXML
    private RadioButton isRing;

    @FXML
    private RadioButton isNecklace;

    @FXML
    private RadioButton isEarring;

    @FXML
    private TextArea basesList;

    @FXML
    private TextField choosedBase;

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
    void AddEarringBase(ActionEvent event) {
        try {
            String title = nameField.getText();
            List<Stone> stonesToUse = new ArrayList<>();
            if (choosedStones.getText().isEmpty() && remoteClient.getStones().isEmpty()){
            } else stonesToUse = getStonesToUse(prepareString(addDot(choosedStones.getText())));

            switch (getType()) {
                case 1:
                    RingBase base = remoteClient.getRingBases().get(Integer.parseInt(choosedBase.getText()) - 1);
                    Adornment adornment = new Adornment(title, base, stonesToUse);
                    remoteClient.addAdornmentOnStock(adornment);
                    break;
                case 2:
                    EarringBase base1 = remoteClient.getEarringBases().get(Integer.parseInt(choosedBase.getText()) - 1);
                    Adornment adornment1 = new Adornment(title, base1, stonesToUse);
                    remoteClient.addAdornmentOnStock(adornment1);
                    break;
                case 3:
                    NecklaceBase base2 = remoteClient.getNecklaceBases().get(Integer.parseInt(choosedBase.getText()) - 1);
                    Adornment adornment2 = new Adornment(title, base2, stonesToUse);
                    remoteClient.addAdornmentOnStock(adornment2);
                    break;
                default:
                    break;
            }
            remoteClient.printMessageOnServer("Было добавлено украшение! Название:"+title);
        } catch (InvalidFieldValueException e) {
            System.out.println("Ошибка вводимых значений!");
        } catch (NumberFormatException ex) {
            System.out.println("Ошибка форматов! / Не введены все значения!");
        }
    }

    private String addDot(String s) {
        return "," + s + ",";
    }

    private List<Integer> prepareString(String s) {
        List<Integer> numbers = new ArrayList<>();
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ',') {
                start = i;
                for (int j = start + 1; j < s.length(); j++) {
                    if (s.charAt(j) == ',') {
                        end = j;
                        String sub = s.substring(start + 1, end);
                        numbers.add((Integer.parseInt(sub)));
                        i = end - 1;
                        break;
                    }
                }
            }
        }
        return numbers;
    }

    private void checkValues(List<Integer> list) throws InvalidFieldValueException {
        for (Integer i : list) {
            if (i > remoteClient.getStones().size() || i <= 0) {
                throw new InvalidFieldValueException();
            }
        }
    }

    private List<Stone> getStonesToUse(List<Integer> numbers) throws InvalidFieldValueException {
        checkValues(numbers);
        List<Stone> stonesToUse = new ArrayList<>();
        for (Integer i : numbers) {
            stonesToUse.add(remoteClient.getStones().get(i - 1));
        }
        return stonesToUse;
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
    void getVariants(ActionEvent event) {
        switch (getType()) {
            case 1:
                basesList.setText(remoteClient.getTitles(remoteClient.getRingBases()));
                break;
            case 2:
                basesList.setText(remoteClient.getTitles(remoteClient.getEarringBases()));
                break;
            case 3:
                basesList.setText(remoteClient.getTitles(remoteClient.getNecklaceBases()));
                break;
            default:
                break;
        }
    }

    @FXML
    void initialize() {
        mainFX = new MainFX();
        remoteClient = mainFX.getRemoteClient();
        serialization = mainFX.getSerialization();
        stonesListField.setText(remoteClient.getTitles(remoteClient.getStones()));
    }

    private int getType() {
        if (isRing.isSelected()) {
            isEarring.setSelected(false);
            isNecklace.setSelected(false);
            return 1;
        } else if (isEarring.isSelected()) {
            isRing.setSelected(false);
            isNecklace.setSelected(false);
            return 2;
        } else if (isNecklace.isSelected()) {
            isEarring.setSelected(false);
            isRing.setSelected(false);
            return 3;
        }
        return 0;
    }
}
