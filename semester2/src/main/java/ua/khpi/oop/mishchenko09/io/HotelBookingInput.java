package ua.khpi.oop.mishchenko09.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko09.model.HotelRoom;
import ua.khpi.oop.mishchenko09.model.Person;

import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class HotelBookingInput {
    private static final Logger logger = LoggerFactory.getLogger(HotelBookingInput.class);
    private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public static HotelBooking inputForm(Scanner scanner, PrintStream printStream) {
        String passportId = null;
        String surname = null;
        String name = null;
        String patronymic = null;
        LocalDate dayOfBirth = null;
        LocalDate settlementDate = null;
        LocalDate evictionDate = null;
        int numberOfRoom = 0;
        ClassOfRoom classOfRoom = null;
        int countOfPlaces = 0;
        ArrayList<String> settlementReason = new ArrayList<>();
        String exit = null;
        int insert = 0;
        HotelBookingGUI hotelBookingGUI = new HotelBookingGUI(printStream);

        do {
            hotelBookingGUI.printInputMenu();
            exit = scanner.next();
            try {
                insert = Integer.parseInt(exit);
            } catch (NumberFormatException e) {
                logger.warn("Incorrect input");
            }

            switch (insert) {
                case 0:
                    break;
                case 1:
                    printStream.println("Enter passport ID:");
                    passportId = inStr(scanner);
                    break;
                case 2:
                    printStream.println("Enter surname");
                    surname = inStr(scanner);
                    break;

                case 3:
                    printStream.println("Name");
                    name = inStr(scanner);
                    break;

                case 4:
                    printStream.println("Enter patronymic");
                    patronymic = inStr(scanner);
                    break;

                case 5:
                    printStream.println("Enter date of birth");
                    dayOfBirth = inDate(scanner);
                    break;

                case 7:
                    printStream.println("Enter settlement date");
                    settlementDate = inDate(scanner);
                    break;

                case 8:
                    printStream.println("Enter eviction date");
                    evictionDate = inDate(scanner);
                    break;

                case 9:
                    printStream.println("Enter number of room");
                    numberOfRoom = inInt(scanner);
                    break;

                case 10:
                    printStream.println("Enter class of room");
                    classOfRoom = inClass(scanner);
                    break;

                case 11:
                    printStream.println("Enter count of places of room");
                    countOfPlaces = inInt(scanner);
                    break;

                case 12:
                    printStream.print("Enter reasons \n\t\t(use exit to stop adding): ");
                    settlementReason = settlementReasons(scanner);
                    break;

                case 13:

                    HotelBookingView.showElem(passportId, surname, name, patronymic, dayOfBirth, settlementDate, evictionDate, numberOfRoom, classOfRoom, countOfPlaces, settlementReason);
                    GUI.waitForEnter();
                    break;

                case 14:
                    try {
                        HotelBooking hotelBooking = new HotelBooking(
                                new Person(passportId, surname, name, patronymic, dayOfBirth), settlementDate, evictionDate,
                                new HotelRoom(numberOfRoom, classOfRoom, countOfPlaces), settlementReason);
                        if (!hotelBooking.checkNotNull()) {
                            logger.error("One or more fields is null");
                            GUI.waitForEnter();
                            break;
                        }
                        GUI.cls();
                        return hotelBooking;
                    } catch (Exception e) {
                        logger.error("Unchecked exception occurred");
                        GUI.waitForEnter();
                    }

            }
            GUI.cls();
        } while (!exit.equals("0"));
        return null;
    }

    private static ArrayList<String> settlementReasons(Scanner scanner) {
        ArrayList<String> settlementReasons = new ArrayList<>();
        String line = inStr(scanner);
        while (!line.equals("exit")) {
            if (!line.isEmpty()) settlementReasons.add(line);
            System.out.println("Enter next");
            line = inStr(scanner);

        }
        return settlementReasons;
    }


    public static int inInt(Scanner scanner) {
        int value = 0;
        try {
            value = scanner.nextInt();
        } catch (InputMismatchException e) {
            logger.warn("Incorrect input");
        }
        return value;
    }

    public static String inStr(Scanner scanner) {
        String value = null;
        try {
            value = new Scanner(System.in).nextLine();
        } catch (Exception e) {
            logger.warn("An error occurred");
        }
        return value;
    }

    public static boolean inBool(Scanner scanner) {
        boolean value = false;
        try {
            value = scanner.nextBoolean();
        } catch (Exception e) {
            logger.warn("An error occurred");
        }
        return value;
    }

    public static ClassOfRoom inClass(Scanner scanner) {
        ClassOfRoom classOfRoom = null;
        while (classOfRoom == null) {
            try {
                String s = scanner.next();
                for (ClassOfRoom c : ClassOfRoom.values()) {
                    if (s.toLowerCase().equals(c.getNumberClass())) classOfRoom = c;
                }
            } catch (Exception e) {
                logger.warn("An error occurred");
            }
        }
        return classOfRoom;
    }

    public static LocalDate inDate(Scanner scanner) {
        boolean acceptable = false;
        LocalDate localDate = null;
        while (!acceptable) {
            try {
                String date = scanner.next();
                localDate = LocalDate.parse(date, pattern);
                acceptable = true;
            } catch (DateTimeParseException ignored) {

            }
        }
        return localDate;
    }

    public static int deleteForm(Scanner scanner, PrintStream printStream) {
        HotelBooking deleted = null;
        printStream.println("Enter id of client to delete");
        return inInt(scanner) - 1;
    }

}
