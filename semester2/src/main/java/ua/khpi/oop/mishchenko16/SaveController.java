package ua.khpi.oop.mishchenko16;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import ua.khpi.oop.mishchenko15.io.HotelBookingToXML;

import java.io.File;

public class SaveController {

    @FXML
    private TextField directoryField;

    @FXML
    private Button chooseButton;

    @FXML
    private TextField filenameField;

    @FXML
    private Button saveButton;

    @FXML
    void initialize() {
        chooseButton.setOnAction(actionEvent -> {
            DirectoryChooser chooser = new DirectoryChooser();
            chooser.setTitle("Save file");
            File defaultDirectory = new File(".");
            chooser.setInitialDirectory(defaultDirectory);
            File selectedDirectory = chooser.showDialog((((Button) actionEvent.getSource()).getScene().getWindow()));
            try {
                directoryField.setText(selectedDirectory.toString());
            } catch (NullPointerException ignored) {

            }
        });

        saveButton.setOnAction(actionEvent -> {
            if (new File(directoryField.getText()).exists()) {
                if (filenameField.getText().endsWith(".xml")) {
                    String path = directoryField.getText().concat(File.separator).concat(filenameField.getText());
                    new HotelBookingToXML().write(Collections.list, path);
                    saveButton.getScene().getWindow().hide();
                } else {
                    Warning.showWarnWithHeaderText("File should be .xml");
                }
            } else {
                Warning.showErrorWithHeaderText("FILE NOT FOUND!");
            }
        });
    }


}
