package ua.khpi.oop.mishchenko15.model;

import ua.khpi.oop.mishchenko09.io.GUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko09.io.RegexCheck;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko15.io.HotelBookingGUI;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Scanner;

public class HotelBookingSearchMaster {

    public static void search(List<HotelBooking> list, PrintStream output, Scanner scanner) {
        boolean exit = false;
        HotelBookingGUI hotelBookingGUI = new HotelBookingGUI(output);
        while (!exit) {
           hotelBookingGUI.searchMenu();
            int choice = HotelBookingInput.inInt(scanner);
            switch (choice) {
                case 1:
                    output.println("Enter surname to search");
                    printClientsBySurname(list, HotelBookingInput.inStr(scanner), output);
                    break;
                case 2:
                    output.println("Enter age range");
                    printClientsByAge(list, HotelBookingInput.inInt(scanner), HotelBookingInput.inInt(scanner), output);
                    break;
                case 3:
                    output.println("Enter class of room to search");
                    printClientsByClass(list, HotelBookingInput.inClass(scanner), output);
                    break;
                case 4:
                    output.println("Enter key word of settlement reason to search clients");
                    searchBySettlementReason(list, output, HotelBookingInput.inStr(scanner));
                    break;
                case 0:
                    exit = true;
                    break;
            }
        }
    }

    private static void printClientsBySurname(List<HotelBooking> list, String surname, PrintStream output) {
        for (HotelBooking e : list) {
            if (surname.equalsIgnoreCase(e.getPerson().getSurname())) {
                output.println(e);
            }
        }
    }

    private static void printClientsByAge(List<HotelBooking> list, int start, int end, PrintStream output) {
        for (HotelBooking e : list) {
            int age = Period.between(e.getPerson().getDayOfBirth(), LocalDate.now()).getYears();
            if (age > start && age < end) {
                output.println(e);
            }
        }
    }

    private static void printClientsByClass(List<HotelBooking> list, ClassOfRoom classOfRoom, PrintStream output) {
        for (HotelBooking e : list) {
            if (classOfRoom.getNumberClass().equals(e.getHotelRoom().getClassOfRoom().getNumberClass())) {
                output.println(e);
            }
        }
    }


    private static void searchBySettlementReason(List<HotelBooking> list, PrintStream target, String key) {
        for (HotelBooking client : list) {
            boolean isPrinted = false;
            for (String reason : client.getSettlementReasons()) {
                if (RegexCheck.searchByReason(reason, key)) {
                    isPrinted = true;
                }
            }
            if (isPrinted) target.println(client);
        }
        GUI.waitForEnter();
        GUI.cls();
    }
}
