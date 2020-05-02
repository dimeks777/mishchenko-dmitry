package ua.khpi.oop.mishchenko15.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko15.model.HotelBookingGenerator;
import ua.khpi.oop.mishchenko14.model.AppStates;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko15.io.HotelBookingGUI;
import ua.khpi.oop.mishchenko15.model.MultiThreadingStrategy;
import ua.khpi.oop.mishchenko15.io.HotelBookingToXML;
import ua.khpi.oop.mishchenko15.io.HotelBookingView;
import ua.khpi.oop.mishchenko15.io.SerializationInterface;
import ua.khpi.oop.mishchenko15.model.HotelBookingSortingComparators;
import ua.khpi.oop.mishchenko15.model.HotelBookingSearchMaster;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Loop {

    private static final Logger logger = LoggerFactory.getLogger(Loop.class);
    private final InputStream input;
    private final PrintStream output;

    public Loop(InputStream input, PrintStream output) {
        this.input = input;
        this.output = output;
    }

    public void loop(String[] args) {
        logger.info("App started");
        ArrayList<HotelBooking> clientList = new ArrayList<>();
        if (checkAutoModeArg(args)) {
            clientList = autoLoad();
        }
        HotelBookingView<HotelBooking> view = new HotelBookingView<>(clientList);
        HotelBookingGUI hotelBookingGUI = new HotelBookingGUI(output);
        String scan = "";
        int in = 0;
        var scanner = new Scanner(input);

        do {
            hotelBookingGUI.printMainMenu();
            output.println("Choose option");
            try {
                scan = scanner.next();
                in = Integer.parseInt(scan);
            } catch (NumberFormatException e) {
                logger.warn("Incorrect input");
            }

            switch (in) {
                case AppStates.EXIT:
                    break;
                case AppStates.INSERT:
                    clientList.add(HotelBookingInput.inputForm(scanner, output));
                    break;
                case AppStates.DELETE:
                    clientList.remove(HotelBookingInput.deleteForm(scanner, output));
                    break;
                case AppStates.DELETE_ALL:
                    clientList.clear();
                    break;
                case AppStates.SHOW_ALL:
                    view.printView(output);
                    break;

                case AppStates.SERIALIZATION_SAVE:
                    SerializationInterface.save(output, scanner, clientList);
                    break;

                case AppStates.SERIALIZATION_LOAD:
                    clientList = SerializationInterface.load(output, scanner);
                    break;

                case AppStates.TO_STRING:
                    output.println(clientList);
                    break;

                case AppStates.TO_ARRAY:
                    view.printArray(clientList.toArray(new HotelBooking[0]), output);
                    break;

                case AppStates.SORT:
                    HotelBookingSortingComparators.sorting(clientList, scanner, output);
                    break;

                case AppStates.SEARCH:
                    HotelBookingSearchMaster.search(clientList, output, scanner);
                    break;

                case AppStates.MULTI_THREADING:
                    new MultiThreadingStrategy(clientList, output, scanner).start();
                    break;

                case AppStates.COMPARE_MULTITHREADING:
                    new MultiThreadingStrategy(clientList, output, scanner).compare();
                    break;

                case AppStates.GENERATE:
                    clientList = HotelBookingGenerator.generator(scanner, output);
            }
            view.updateView(clientList);

        } while (!(scan.equals(String.valueOf(AppStates.EXIT))));
        logger.info("End");
    }

    private boolean checkAutoModeArg(String[] args) {
        boolean auto = false;

        for (String s1 : args) {
            if (s1.equals("-auto")) {
                return true;
            }
        }
        return false;
    }

    private ArrayList<HotelBooking> autoLoad() {
        return new HotelBookingToXML().read(Loop.class.getResource("/gen15.xml").getPath());
    }


}
