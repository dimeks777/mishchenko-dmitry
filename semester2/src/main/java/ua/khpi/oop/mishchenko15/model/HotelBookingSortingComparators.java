package ua.khpi.oop.mishchenko15.model;

import ua.khpi.oop.mishchenko09.io.HotelBookingGUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.io.PrintStream;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class HotelBookingSortingComparators {

    public static void sorting(ArrayList<HotelBooking> input, Scanner in, PrintStream output) {
        boolean exit = false;
        while (!exit) {
            new HotelBookingGUI(output).sorting();
            int choose = HotelBookingInput.inInt(in);
            switch (choose) {
                case 1:
                    sortByLivingDuration(input);
                    break;
                case 2:
                    sortByClassOfRoom(input);
                    break;
                case 3:
                    sortByCountOfPlaces(input);
                    break;
                default:
                    exit = true;
                    break;
            }

        }
    }

    public static void sortByLivingDuration(ArrayList<HotelBooking> input) {
        input.sort(Comparator.comparingInt(o -> Period.between(o.getSettlementDate(), o.getEvictionDate()).getDays()));
    }

    public static void sortByClassOfRoom(ArrayList<HotelBooking> input) {
        input.sort(Comparator.comparing(p -> p.getHotelRoom().getClassOfRoom().getNumberClass()));
    }

    public static void sortByCountOfPlaces(ArrayList<HotelBooking> input) {
        input.sort(Comparator.comparingInt(p -> p.getHotelRoom().getCountOfPlaces()));
    }

}
