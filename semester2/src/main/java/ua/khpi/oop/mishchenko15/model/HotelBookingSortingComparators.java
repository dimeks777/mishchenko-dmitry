package ua.khpi.oop.mishchenko15.model;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.io.HotelBookingGUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.io.PrintStream;
import java.time.Period;
import java.util.*;

public class HotelBookingSortingComparators {

    public static void sorting(ArrayList<HotelBooking> input, Scanner in, PrintStream output) {
        boolean exit = false;
        while (!exit) {
            new HotelBookingGUI(output).sorting();
            int choose = HotelBookingInput.inInt(in);
            switch (choose) {
                case 1:
                    input.sort(Comparator.comparingInt(o -> Period.between(o.getSettlementDate(), o.getEvictionDate()).getDays()));
                    break;
                case 2:
                    input.sort(Comparator.comparing(p -> p.getHotelRoom().getClassOfRoom().getNumberClass()));
                    break;
                case 3:
                    input.sort(Comparator.comparingInt(p -> p.getHotelRoom().getCountOfPlaces()));
                    break;
                default:
                    exit = true;
                    break;
            }

        }
    }
}
