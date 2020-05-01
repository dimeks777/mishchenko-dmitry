package ua.khpi.oop.mishchenko09.model;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.io.HotelBookingGUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;

import java.io.PrintStream;
import java.time.Period;
import java.util.Comparator;
import java.util.Scanner;

public class HotelBookingSortingComparators {

    public static void sorting(ForwardLinkedList<HotelBooking> input, Scanner in, PrintStream output) {
        boolean exit = false;
        while (!exit) {
            new HotelBookingGUI(output).sorting();
            int choose = HotelBookingInput.inInt(in);
            switch (choose) {
                case 1:
                    input.sort(Comparator.comparingInt(p -> Period.between(p.getSettlementDate(), p.getEvictionDate()).getDays()), input.toArray(new HotelBooking[input.size()]));
                    break;
                case 2:
                    input.sort(Comparator.comparing(p -> p.getHotelRoom().getClassOfRoom().getNumberClass()), input.toArray(new HotelBooking[input.size()]));
                    break;
                case 3:
                    input.sort(Comparator.comparingInt(p -> p.getHotelRoom().getCountOfPlaces()), input.toArray(new HotelBooking[input.size()]));
                    break;
                default:
                    exit = true;
                    break;
            }

        }
    }
}
