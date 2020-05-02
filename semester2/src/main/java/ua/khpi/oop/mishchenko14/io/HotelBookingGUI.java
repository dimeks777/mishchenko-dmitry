package ua.khpi.oop.mishchenko14.io;

import ua.khpi.oop.mishchenko09.io.GUI;

import java.io.PrintStream;

public class HotelBookingGUI implements GUI {
    private final PrintStream output;

    public HotelBookingGUI(PrintStream output) {
        this.output = output;
    }

    @Override
    public void printMainMenu() {
        output.println("01. Add new client");
        output.println("02. Delete client");
        output.println("03. Clear list");
        output.println("04. Show list");
        output.println("05. Save");
        output.println("06. Load");
        output.println("07. Get list as string");
        output.println("08. Get list as array");
        output.println("09. Sort");
        output.println("10. Search by reason");
        output.println("11. Thread demo");
        output.println("12. Parallel vs consequential demo");
        output.println("13. Generate random values");
        output.println("00. Exit");
    }

    @Override
    public void printInputMenu() {
        output.println("1. Enter passport ID");
        output.println("2. Enter surname");
        output.println("3. Enter name");
        output.println("4. Enter patronymic");
        output.println("5. Enter date of birth");
        output.println("6. Enter marry status");
        output.println("7. Enter settlement date");
        output.println("8. Enter eviction date");
        output.println("9. Enter number of room");
        output.println("10. Enter class of room");
        output.println("11. Enter count of places");
        output.println("12. Enter settlement reasons");
        output.println("13. Show input data");
        output.println("14. Confirm and add");
        output.println("0. Exit");
    }

    public void serializationType() {
        output.println("1. Standard serialization (Serializable)");
        output.println("2. Long term persistence model (XML)");
    }

    public void sorting() {
        output.println("Choose sorting criteria");
        output.println("1. By living duration");
        output.println("2. By class of room");
        output.println("3. By count of places");
    }
}
