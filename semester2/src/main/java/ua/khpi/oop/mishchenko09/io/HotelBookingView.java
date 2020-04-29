package ua.khpi.oop.mishchenko09.io;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko09.model.Person;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelBookingView<T extends HotelBooking> implements View<T> {

    private ForwardLinkedList<T> clients;

    public HotelBookingView(ForwardLinkedList<T> clients) {
        this.clients = clients;
    }

    public void printView(PrintStream target) {
        target.println("***********************Hotel clients***********************");
        int i = 1;
        for (T client : clients) {
            target.print("\t\t\t            ID=" + i++);
            target.println(client);
        }
        GUI.waitForEnter();
        GUI.cls();
    }

    @SuppressWarnings("unchecked")
    public void printArray(T[] arr, PrintStream target) {
        target.println("***********************Array test***********************");
        for (T e : clients.toArray((T[]) Array.newInstance(arr.getClass().getComponentType(), clients.size()))) {
            target.println(e);
        }
    }

    public void updateView(ForwardLinkedList<T> clients) {
        this.clients = clients;
    }

    public static void showElem(String passportId,
                                String surname,
                                String name,
                                String patronymic,
                                LocalDate dayOfBirth,
                                LocalDate settlementDate,
                                LocalDate evictionDate,

                                int numberOfRoom,
                                ClassOfRoom classOfRoom,
                                int countOfPlaces, ArrayList<String> settlementReason) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("|  Passport ID  |     Surname     |      Name       |     Patronymic     |   Day of birth  |  Settlement date   |   Eviction date   |   Number of room   |    Class of room    |   Count of places   |");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("|  %9s    |   %10s    |   %10s    |  %10s        |    %10s    |", passportId, surname, name, patronymic, dayOfBirth == null ? null : dayOfBirth.format(Person.DAY_OF_BIRTH_FORMAT));
        System.out.printf("    %10s      |    %10s     |", settlementDate, evictionDate);
        System.out.printf("    %8d        |      %7s        |    %8d         |\n", numberOfRoom, classOfRoom, countOfPlaces);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Settlement reasons:");
        int i = 1;
        for (String s : settlementReason) {
            System.out.println((i++) + ". " + s);
        }
    }

    public void searchBySettlementReason(PrintStream target, Scanner scanner) {
        target.println("Enter key word of settlement reason to search clients");
        String key = HotelBookingInput.inStr(scanner);
        for (HotelBooking client : clients) {
            boolean isPrinted = false;
            boolean isLiving = client.getEvictionDate().isAfter(LocalDate.now());
            for (String reason : client.getSettlementReasons()) {
                if (RegexCheck.searchByReason(reason, key) && isLiving) {
                    isPrinted = true;
                }
            }
            if (isPrinted) target.println(client);
        }
        GUI.waitForEnter();
        GUI.cls();
    }
}
