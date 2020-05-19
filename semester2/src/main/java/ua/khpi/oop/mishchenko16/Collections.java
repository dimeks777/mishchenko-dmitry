package ua.khpi.oop.mishchenko16;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.util.ArrayList;

public class Collections {
    public static final ObservableList<HotelBooking> clientsObservableList = FXCollections.observableArrayList();
    public static final ArrayList<HotelBooking> list = new ArrayList<>();
}
