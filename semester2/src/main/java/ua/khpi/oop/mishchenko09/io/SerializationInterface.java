package ua.khpi.oop.mishchenko09.io;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.io.PrintStream;
import java.util.Scanner;

public class SerializationInterface {

    public static void save(PrintStream output, Scanner scanner, ForwardLinkedList<HotelBooking> list) {
        output.println("Choose file to save");
        String path = DirectoryManager.controller(scanner, output);
        output.println("\n\nChoose type of serialization");
        new HotelBookingGUI(output).serializationType();

        while (true) {
            int choice = HotelBookingInput.inInt(scanner);
            switch (choice) {
                case 1:
                    new StandardFileSerialization().write(list, path);
                    return;
                case 2:
                    new HotelBookingToXML().write(list, path);
                    return;
            }
        }


    }

    public static ForwardLinkedList<HotelBooking> load(PrintStream output, Scanner scanner) {
        output.println("Choose file to load");
        String path = DirectoryManager.controller(scanner, output);
        output.println("\n\nChoose type of decoder");
        new HotelBookingGUI(output).serializationType();
        while (true) {
            int choice = HotelBookingInput.inInt(scanner);
            switch (choice) {
                case 1:
                    return new StandardFileSerialization().read(path);
                case 2:
                    return new HotelBookingToXML().read(path);
            }
        }

    }
}
