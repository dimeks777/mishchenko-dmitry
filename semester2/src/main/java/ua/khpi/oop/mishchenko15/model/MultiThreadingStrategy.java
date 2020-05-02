package ua.khpi.oop.mishchenko15.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.io.GUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko09.io.RegexCheck;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import javax.swing.*;
import java.io.PrintStream;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;

public class MultiThreadingStrategy {
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadingStrategy.class);
    private final ArrayList<HotelBooking> list;
    private final PrintStream output;
    private final Scanner scanner;

    public MultiThreadingStrategy(ArrayList<HotelBooking> list, PrintStream output, Scanner scanner) {
        this.list = list;
        this.output = output;
        this.scanner = scanner;
    }

    public void start() {
        output.println("Set the timer [0 - 100 000 ms]: ");
        int timer_num = HotelBookingInput.inInt(scanner);
        output.println("Starting all threads...");
        FirstThread first = new FirstThread();
        Thread t1 = new Thread(first, "FirstThread");
        SecondThread second = new SecondThread();
        Thread t2 = new Thread(second, "SecondThread");
        ThirdThread third = new ThirdThread();
        Thread t3 = new Thread(third, "ThirdThread");
        t1.start();
        t2.start();
        t3.start();

        Timer timer = new Timer(timer_num, event -> {
            logger.info("Interrupting thread...");
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
        });

        timer.setRepeats(false);
        timer.start();
        try {
            t1.join();
            t2.join();
            t3.join();
            timer.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Finishing all threads...");
        GUI.waitForEnter();
        GUI.cls();
    }

    public void compare() {
        long consequential = runConsequentially();
        long parallel = runParallel();
        output.println("Time of parallel run -> " + parallel);
        output.println("Time of consequential run -> " + consequential);
        output.println("Parallel is faster in " + (double) consequential / parallel + " times");
        GUI.waitForEnter();
        GUI.cls();
    }

    public long runParallel() {
        long time_start = System.currentTimeMillis();
        logger.info("Starting parallel run...");
        FirstThread first = new FirstThread();
        Thread t1 = new Thread(first, "FirstThread");
        SecondThread second = new SecondThread();
        Thread t2 = new Thread(second, "SecondThread");
        ThirdThread third = new ThirdThread();
        Thread t3 = new Thread(third, "ThirdThread");
        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Finishing parallel run...");
        return System.currentTimeMillis() - time_start;

    }

    public long runConsequentially() {
        long time_start = System.currentTimeMillis();
        logger.info("Starting consequential run...");
        try {
            getClientsWithNameCharlesCount();
            getWorkingClientsCount();
            getClientsWithBirthdaysInAprilCount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Finishing consequential run...");
        return System.currentTimeMillis() - time_start;

    }

    public void getClientsWithNameCharlesCount() throws InterruptedException {
        int count = 0;
        for (HotelBooking client : list) {
            if (!Thread.currentThread().isInterrupted()) {
                if (client.getPerson().getName().equals("Charles")) {
                    count++;
                }
            } else {
                throw new InterruptedException();
            }
        }
    }

    public void getWorkingClientsCount() throws InterruptedException {
        int count = 0;
        for (HotelBooking client : list) {
            if (!Thread.currentThread().isInterrupted()) {
                if (RegexCheck.searchByReason(client.getSettlementReasons().toString(), "business trip")) {
                    count++;
                }
            } else {
                throw new InterruptedException();
            }
        }
    }

    public void getClientsWithBirthdaysInAprilCount() throws InterruptedException {
        int count = 0;
        for (HotelBooking client : list) {
            if (!Thread.currentThread().isInterrupted()) {
                if (client.getPerson().getDayOfBirth().getMonth() == Month.APRIL) {
                    count++;
                }
            } else {
                throw new InterruptedException();
            }
        }
    }

    private class FirstThread implements Runnable {
        public void run() {
            try {
                getClientsWithNameCharlesCount();
                logger.info("{} finished",Thread.currentThread().getName());
            } catch (InterruptedException ignored) {
            }
        }
    }

    private class SecondThread implements Runnable {
        public void run() {
            try {
                getWorkingClientsCount();
                logger.info("{} finished",Thread.currentThread().getName());
            } catch (InterruptedException ignored) {
            }
        }
    }

    private class ThirdThread implements Runnable {
        public void run() {
            try {
                getClientsWithBirthdaysInAprilCount();
                logger.info("{} finished",Thread.currentThread().getName());
            } catch (InterruptedException ignored) {
            }
        }
    }
}



