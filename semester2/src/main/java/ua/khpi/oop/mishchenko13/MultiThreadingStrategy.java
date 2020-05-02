package ua.khpi.oop.mishchenko13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.io.GUI;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko09.io.RegexCheck;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import javax.swing.*;
import java.io.PrintStream;
import java.time.Month;
import java.util.Scanner;

public class MultiThreadingStrategy {
    private static final Logger logger = LoggerFactory.getLogger(MultiThreadingStrategy.class);
    private final ForwardLinkedList<HotelBooking> list;
    private final PrintStream output;
    private final Scanner scanner;

    public MultiThreadingStrategy(ForwardLinkedList<HotelBooking> list, PrintStream output, Scanner scanner) {
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

    private class FirstThread implements Runnable {
        public void run() {
            int count = 0;
            logger.info("First Thread started");
            try {
                for (HotelBooking client : list) {
                    if (!Thread.currentThread().isInterrupted()) {
                        if (client.getPerson().getName().equals("Charles")) {
                            count++;
                        }
                    } else {
                        throw new InterruptedException();
                    }
                }
                logger.info("First Thread finished");
                output.println("People with name Charles : " + count);
            } catch (InterruptedException e) {
                logger.info("First Thread is interrupted");
            }
        }
    }

    private class SecondThread implements Runnable {
        public void run() {
            int count = 0;
            logger.info("Second Thread started");
            try {
                for (HotelBooking client : list) {
                    if (!Thread.currentThread().isInterrupted()) {
                        if (RegexCheck.searchByReason(client.getSettlementReasons().toString(), "business trip")) {
                            count++;
                        }
                    } else {
                        throw new InterruptedException();
                    }
                }
                logger.info("Second Thread finished");
                output.println("People in business trip: " + count);
            } catch (InterruptedException e) {
                logger.info("Second Thread is interrupted");
            }
        }
    }

    private class ThirdThread implements Runnable {
        public void run() {
            int count = 0;
            logger.info("Third Thread started");
            try {
                for (HotelBooking client : list) {
                    if (!Thread.currentThread().isInterrupted()) {
                        if (client.getPerson().getDayOfBirth().getMonth() == Month.APRIL) {
                            count++;
                        }
                    } else {
                        throw new InterruptedException();
                    }
                }
                logger.info("Third Thread finished");
                output.println("People with birthday in April: " + count);
            } catch (InterruptedException e) {
                logger.info("Third Thread is interrupted");
            }
        }
    }
}



