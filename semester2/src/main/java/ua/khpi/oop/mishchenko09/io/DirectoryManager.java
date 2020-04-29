package ua.khpi.oop.mishchenko09.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class DirectoryManager {
    private static final Logger logger = LoggerFactory.getLogger(DirectoryManager.class);

    public static String controller(Scanner in, PrintStream output) {

        try {
            String path = new File("").getAbsolutePath();
            String find2 = "";
            int choice = 0;
            while (!"0".equals(find2)) {
                output.println("Current path: " + path + "\n" +
                        "1. Continue\n" +
                        "2. Create file\n" +
                        "3. Another directory\n" +
                        "0. Save\n" +
                        "Choose: ");
                find2 = in.next();
                try {
                    choice = Integer.parseInt(find2);
                } catch (NumberFormatException e) {
                    output.println("Incorrect data!");
                }

                switch (choice) {
                    case 1:
                        path = chooseFile(getListOfFiles(path), in, output);
                        break;
                    case 2:
                        path = createFile(path, in);
                        break;
                    case 3:
                        path = moveHigher(path);
                        break;
                }
            }
            return path;

        } catch (Exception e) {
            logger.error("URISyntaxException");
        }

        return null;
    }


    private static File[] getListOfFiles(String path) {
        File f = new File(path);
        return f.listFiles();

    }


    private static void printListOfFiles(File[] list, PrintStream output) {
        for (int i = 0; i < list.length; i++) {
            output.println(i + 1 + ") " + list[i].getName());
        }
    }

    private static String moveHigher(String path) {
        return new StringBuilder(path).delete(path.lastIndexOf(File.separator), path.length()).toString();
    }

    private static String chooseFile(File[] list, Scanner in, PrintStream output) {
        printListOfFiles(list, output);
        output.println("Choose package or file:");
        int index = in.nextInt();

        return list[index - 1].getAbsolutePath();

    }

    private static String createFile(String path, Scanner in) {
        String newPath = path + File.separator + HotelBookingInput.inStr(in);
        return new File(newPath).getAbsolutePath();

    }
}
