package ua.khpi.oop.mishchenko09.io;

import java.util.Scanner;

public interface GUI {
    void printMainMenu();

    static void cls() {
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }

    void printInputMenu();

    static void waitForEnter() {
        System.out.println("Enter any key to continue...");
        new Scanner(System.in).nextLine();
    }
}
