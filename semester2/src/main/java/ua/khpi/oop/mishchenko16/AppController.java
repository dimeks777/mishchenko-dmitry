package ua.khpi.oop.mishchenko16;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;
import ua.khpi.oop.mishchenko09.io.RegexCheck;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko15.model.HotelBookingGenerator;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class AppController {
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @FXML
    private TableView<HotelBooking> table;

    @FXML
    private TableColumn<HotelBooking, String> passportIdColumn;

    @FXML
    private TableColumn<HotelBooking, String> surnameColumn;

    @FXML
    private TableColumn<HotelBooking, String> nameColumn;

    @FXML
    private TableColumn<HotelBooking, String> patronymicColumn;

    @FXML
    private TableColumn<HotelBooking, LocalDate> dayOfBirthColumn;

    @FXML
    private TableColumn<HotelBooking, LocalDate> settlementDateColumn;

    @FXML
    private TableColumn<HotelBooking, LocalDate> evictionDateColumn;

    @FXML
    private TableColumn<HotelBooking, Integer> roomNumberColumn;

    @FXML
    private TableColumn<HotelBooking, ClassOfRoom> classOfRoomColumn;

    @FXML
    private TableColumn<HotelBooking, Integer> countOfPlacesColumn;

    @FXML
    private TableColumn<HotelBooking, String> settlementReasonsColumn;

    @FXML
    private Button signUpButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button sortButton;

    @FXML
    private Button searchButton;

    @FXML
    private RadioButton livingDurationSortRadio;

    @FXML
    private RadioButton classOfRoomSortRadio;

    @FXML
    private RadioButton countOfPlacesSortRadio;

    @FXML
    private RadioButton surnameSearchRadio;

    @FXML
    private RadioButton ageSearchRadio;

    @FXML
    private RadioButton classOfRoomSearchRadio;

    @FXML
    private RadioButton settlementReasonSearchRadio;

    @FXML
    private TextField searchPredicateField;

    @FXML
    private TextField countOfGeneratedField;

    @FXML
    private Button generateButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button loadDataButton;

    @FXML
    private Button saveDataButton;

    @FXML
    private Button deleteButton;

    @FXML
    void initialize() {

        passportIdColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getPerson().getPassportId()));
        surnameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getPerson().getSurname()));
        nameColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getPerson().getName()));
        patronymicColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getPerson().getPatronymic()));
        dayOfBirthColumn.setCellValueFactory(cellDataFeatures -> new SimpleObjectProperty<>(cellDataFeatures.getValue().getPerson().getDayOfBirth()));

        settlementDateColumn.setCellValueFactory(new PropertyValueFactory<>("settlementDate"));
        evictionDateColumn.setCellValueFactory(new PropertyValueFactory<>("evictionDate"));
        roomNumberColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getHotelRoom().getNumberOfRoom()).asObject());
        classOfRoomColumn.setCellValueFactory(cellDataFeatures -> new SimpleObjectProperty<>(cellDataFeatures.getValue().getHotelRoom().getClassOfRoom()));
        countOfPlacesColumn.setCellValueFactory(cellDataFeatures -> new SimpleIntegerProperty(cellDataFeatures.getValue().getHotelRoom().getCountOfPlaces()).asObject());
        settlementReasonsColumn.setCellValueFactory(cellDataFeatures -> new SimpleStringProperty(cellDataFeatures.getValue().getSettlementReasons().stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"))));
        table.setItems(Collections.clientsObservableList);

        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        passportIdColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        passportIdColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getPerson().setPassportId(e.getNewValue());
        });

        surnameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        surnameColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getPerson().setSurname(e.getNewValue());
        });

        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getPerson().setName(e.getNewValue());
        });

        patronymicColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        patronymicColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getPerson().setPatronymic(e.getNewValue());
        });

        dayOfBirthColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        dayOfBirthColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getPerson().setDayOfBirth(e.getNewValue());
        });

        settlementDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        settlementDateColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSettlementDate(e.getNewValue());
        });

        evictionDateColumn.setCellFactory(TextFieldTableCell.forTableColumn(new LocalDateStringConverter()));
        evictionDateColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEvictionDate(e.getNewValue());
        });

        roomNumberColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        roomNumberColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getHotelRoom().setNumberOfRoom((e.getNewValue()));
        });

        classOfRoomColumn.setCellFactory(TextFieldTableCell.forTableColumn(new StringConverter<>() {
            @Override
            public String toString(ClassOfRoom classOfRoom) {
                return classOfRoom.getNumberClass();
            }

            @Override
            public ClassOfRoom fromString(String s) {
                for (ClassOfRoom c : ClassOfRoom.values()) {
                    if (s.toLowerCase().equals(c.getNumberClass())) return c;
                }
                return null;
            }
        }));
        classOfRoomColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getHotelRoom().setClassOfRoom((e.getNewValue()));
        });

        countOfPlacesColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        countOfPlacesColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).getHotelRoom().setCountOfPlaces((e.getNewValue()));
        });

        settlementReasonsColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        settlementReasonsColumn.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setSettlementReasons(new ArrayList<>() {
                {
                    remove(e.getOldValue());
                    add(e.getNewValue() + "\n");
                }
            });
        });

        table.setEditable(true);
        sortButton.setOnAction(actionEvent -> {
            if (livingDurationSortRadio.isSelected()) {
                Collections.clientsObservableList.sort(Comparator.comparingInt(o -> Period.between(o.getSettlementDate(), o.getEvictionDate()).getDays()));
            } else if (classOfRoomSortRadio.isSelected()) {
                Collections.clientsObservableList.sort(Comparator.comparing(p -> p.getHotelRoom().getClassOfRoom().getNumberClass()));
            } else {
                Collections.clientsObservableList.sort(Comparator.comparingInt(p -> p.getHotelRoom().getCountOfPlaces()));
            }
        });

        searchButton.setOnAction(actionEvent -> {
            Collections.clientsObservableList.clear();
            String searchPredicate = searchPredicateField.getText();
            if (surnameSearchRadio.isSelected()) {
                for (HotelBooking e : Collections.list) {
                    if (searchPredicate.equalsIgnoreCase(e.getPerson().getSurname())) {
                        Collections.clientsObservableList.add(e);
                    }
                }
            } else if (ageSearchRadio.isSelected()) {
                String[] ages = searchPredicate.split("-");
                for (HotelBooking e : Collections.list) {
                    int age = Period.between(e.getPerson().getDayOfBirth(), LocalDate.now()).getYears();
                    if (age > Integer.parseInt(ages[0]) && age < Integer.parseInt(ages[1])) {
                        Collections.clientsObservableList.add(e);
                    }
                }
            } else if (classOfRoomSearchRadio.isSelected()) {
                ClassOfRoom classOfRoom = null;
                for (ClassOfRoom c : ClassOfRoom.values()) {
                    if (searchPredicate.toLowerCase().equals(c.getNumberClass())) classOfRoom = c;
                }
                if (classOfRoom != null) {
                    for (HotelBooking e : Collections.list) {
                        if (classOfRoom.getNumberClass().equals(e.getHotelRoom().getClassOfRoom().getNumberClass())) {
                            Collections.clientsObservableList.add(e);
                        }
                    }
                }
            } else {
                for (HotelBooking client : Collections.list) {
                    boolean accepted = false;
                    for (String reason : client.getSettlementReasons()) {
                        if (RegexCheck.searchByReason(reason, searchPredicate)) {
                            accepted = true;
                        }
                    }
                    if (accepted) Collections.clientsObservableList.add(client);
                }
            }
        });

        generateButton.setOnAction(actionEvent -> {
            int count = Integer.parseInt(countOfGeneratedField.getText());
            Collections.clientsObservableList.clear();
            Collections.list.clear();
            Collections.list.addAll(HotelBookingGenerator.generateList(count));
            Collections.clientsObservableList.addAll(Collections.list);
        });

        resetButton.setOnAction(actionEvent -> {
            Collections.clientsObservableList.clear();
            Collections.clientsObservableList.addAll(Collections.list);
        });

        signUpButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/add.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        exitButton.setOnAction(actionEvent -> {
            exitButton.getScene().getWindow().hide();
        });

        clearButton.setOnAction(actionEvent -> {
            Collections.list.clear();
            Collections.clientsObservableList.clear();
        });

        saveDataButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/save.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        loadDataButton.setOnAction(actionEvent -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/load.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();
        });

        deleteButton.setOnAction(actionEvent -> {
            ObservableList<HotelBooking> accountsSelected = table.getSelectionModel().getSelectedItems();
            ArrayList<HotelBooking> items = new ArrayList<>(table.getSelectionModel().getSelectedItems());
            Collections.clientsObservableList.removeAll(accountsSelected);
            table.getSelectionModel().clearSelection();
            Collections.list.removeAll(items);
        });
    }
}