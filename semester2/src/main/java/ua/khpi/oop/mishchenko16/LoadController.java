package ua.khpi.oop.mishchenko16;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import ua.khpi.oop.mishchenko15.io.HotelBookingToXML;

import java.io.File;

public class LoadController {
    private String path;

    @FXML
    private Button chooseButton;


    @FXML
    private Button loadButton;

    @FXML
    private TextField directoryField;

    @FXML
    void initialize() {
        chooseButton.setOnAction(actionEvent -> {
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Load file");
            File defaultDirectory = new File(".");
            chooser.setInitialDirectory(defaultDirectory);
            File selectedFile = chooser.showOpenDialog((((Button) actionEvent.getSource()).getScene().getWindow()));
            try {
                if (selectedFile.getName().endsWith(".xml")) {
                    path = selectedFile.getPath();
                        directoryField.setText(path);
                }
            } catch (NullPointerException ignored) {

            }
        });

        loadButton.setOnAction(actionEvent -> {
            if (new File(path).exists()) {
                Collections.list.clear();
                Collections.clientsObservableList.clear();
                Collections.list.addAll(new HotelBookingToXML().read(path));
                Collections.clientsObservableList.addAll(Collections.list);
                loadButton.getScene().getWindow().hide();
            }
        });
    }
}