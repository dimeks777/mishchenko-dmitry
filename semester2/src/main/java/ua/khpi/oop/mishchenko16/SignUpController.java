package ua.khpi.oop.mishchenko16;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import ua.khpi.oop.mishchenko09.io.RegexCheck;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko09.model.HotelRoom;
import ua.khpi.oop.mishchenko09.model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SignUpController {
    public static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");


    @FXML
    private TextField passportIdField;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField surnameField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField numberOfRoomField;

    @FXML
    private TextField classOfRoomField;

    @FXML
    private TextField countOfPlacesField;

    @FXML
    private TextField patronymicField;

    @FXML
    private TextField settlementDateField;

    @FXML
    private TextField dayOfBirthField;

    @FXML
    private TextField evictionDateField;

    @FXML
    private TextArea settlementReasonsField;

    @FXML
    void initialize() {
        signUpButton.setOnAction(actionEvent -> {

            StringBuilder sb = new StringBuilder();
            boolean add = true;
            int i = 1;
            String passportID = passportIdField.getText();
            String surname = surnameField.getText();
            String name = nameField.getText();
            String patronymic = patronymicField.getText();
            String dob = dayOfBirthField.getText();
            String settleDate = settlementDateField.getText();
            String evicDate = evictionDateField.getText();
            String numberOfRoom = numberOfRoomField.getText();
            int roomNumber = 0;
            String countOfPlaces = countOfPlacesField.getText();
            int count = 0;
            String classOR = classOfRoomField.getText();
            ClassOfRoom classOfRoom = null;
            String settlementReasons = settlementReasonsField.getText();
            LocalDate dayOfBirth = null;
            LocalDate settlementDate = null;
            LocalDate evictionDate = null;


            if (!RegexCheck.checkPassportID(passportID)) {
                add = false;
                sb.append(i++).append(") Error in passport ID. Should be (ex. 123456789) or (ex. AX123456)\n");
            }

            if (!RegexCheck.checkSNP(surname)) {
                add = false;
                sb.append(i++).append(") Error in surname. Should not contain digits or special symbols\n");
            }
            if (!RegexCheck.checkSNP(name)) {
                add = false;
                sb.append(i++).append(") Error in surname. Should not contain digits or special symbols\n");
            }
            if (!RegexCheck.checkSNP(patronymic)) {
                add = false;
                sb.append(i++).append(") Error in surname. Should not contain digits or special symbols\n");
            }

            try {
                dayOfBirth = LocalDate.parse(dob, pattern);
                if (dayOfBirth == null) add = false;
            } catch (Exception e) {
                sb.append(i++).append(") Error in day of birth date. Should be dd-MM-yyyy (ex. 10-02-2020)\n");
            }

            try {
                settlementDate = LocalDate.parse(settleDate, pattern);
                if (settlementDate == null) add = false;
            } catch (Exception e) {
                sb.append(i++).append(") Error in settlement date. Should be dd-MM-yyyy (ex. 10-02-2020)\n");
            }

            try {
                evictionDate = LocalDate.parse(evicDate, pattern);
                if (evictionDate == null) add = false;
            } catch (Exception e) {
                sb.append(i++).append(") Error in eviction date. Should be dd-MM-yyyy (ex. 10-02-2020)\n");
            }

            if (!RegexCheck.checkNumberOfRoom(numberOfRoom)) {
                add = false;
                sb.append(i++).append(") Error in number of room. Should be in range(100-9999)\n");
            } else {
                roomNumber = Integer.parseInt(numberOfRoom);
            }

            for (ClassOfRoom c : ClassOfRoom.values()) {
                if (classOR.toLowerCase().equals(c.getNumberClass())) classOfRoom = c;
            }

            if (classOfRoom == null) {
                add = false;
            } else {
                sb.append(i++).append(") Error in class of room. Should be econom, default or lux\n");
            }

            if (!RegexCheck.checkCountOfPlaces(countOfPlaces)) {
                add = false;
                sb.append(i++).append(") Error in count of places. Should be in range(1-9)\n");
            } else {
                count = Integer.parseInt(countOfPlaces);
            }

            ArrayList<String> settlementReasonsList = new ArrayList<>();
            String[] arr = settlementReasons.split(";");
            for (String e : arr) {
                if (!e.equals("")) {
                    settlementReasonsList.add(e.trim());
                }
            }

            if (add) {
                HotelBooking client = new HotelBooking(
                        new Person(passportID, surname, name, patronymic, dayOfBirth), settlementDate, evictionDate,
                        new HotelRoom(roomNumber, classOfRoom, count), settlementReasonsList);
                Collections.list.add(client);
                Collections.clientsObservableList.add(client);
                signUpButton.getScene().getWindow().hide();
            } else {
                Warning.showAlertWithHeaderText(sb.toString());
            }
        });

    }
}
