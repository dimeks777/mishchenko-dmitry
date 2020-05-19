package ua.khpi.oop.mishchenko15.model;

import ua.khpi.oop.mishchenko09.io.HotelBookingInput;
import ua.khpi.oop.mishchenko15.io.HotelBookingToXML;
import ua.khpi.oop.mishchenko09.model.ClassOfRoom;
import ua.khpi.oop.mishchenko09.model.HotelBooking;
import ua.khpi.oop.mishchenko09.model.HotelRoom;
import ua.khpi.oop.mishchenko09.model.Person;

import java.io.PrintStream;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HotelBookingGenerator {

    public static ArrayList<HotelBooking> generateList(int size) {
        Random random = new Random();
        ArrayList<HotelBooking> list = new ArrayList<>();
        String[] classes = {"econom", "default", "lux"};
        String[] reasons = {"travelling", "business trip", "hiding", "business meeting"};
        String[] surnames = {"Smith", "Johnson", "Williams", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson", "Thomas", "Jackson", "Harris", "Martin", "Thompson", "Martinez", "Robinson", "Clark"};
        String[] names = {"Oliver", "Jack", "Harry", "Jacob", "Charlie", "Thomas", "George", "Oscar", "James", "William", "John", "Robert", "Michael", "David", "Richard", "Joseph", "Charles", "Mason"};
        String[] patronymics = {"Aalbers", "Aarts", "Babayev", "Beatson", "Ben-David", "Clowers", "Davidov", "Ellison", "Enríquez", "Fedorovich", "Fernandes", "Georgiev", "Hermans", "Ibragimov", "Ivanenko", "Jakobsen", "Lajovic", "Karlson"};
        for (int i = 0; i < size; i++) {
            String passportId = String.valueOf((int) ((Math.random() * (999_999_999 - 100_000_000)) + 100_000_000));
            String surname = surnames[random.nextInt(surnames.length)];
            String name = names[random.nextInt(names.length)];
            String patronymic = patronymics[random.nextInt(patronymics.length)];
            long minDay = LocalDate.of(1970, 1, 1).toEpochDay();
            long maxDay = LocalDate.of(2001, 12, 31).toEpochDay();
            long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate dayOfBirth = LocalDate.ofEpochDay(randomDay);
            minDay = LocalDate.of(2020, 1, 1).toEpochDay();
            maxDay = LocalDate.now().toEpochDay();
            randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
            LocalDate settlementDate = LocalDate.ofEpochDay(randomDay);
            LocalDate evictionDate = LocalDate.ofEpochDay(randomDay).plusDays((long) (Math.random() * (30 - 1) + 1));
            int numberOfRoom = (int) (Math.random() * (9999 - 101) + 101);
            ClassOfRoom classOfRoom = null;
            String s = classes[random.nextInt(classes.length)];
            for (ClassOfRoom c : ClassOfRoom.values()) {
                if (s.toLowerCase().equals(c.getNumberClass())) classOfRoom = c;
            }
            int countOfPlaces = (int) (Math.random() * (9 - 1) + 1);
            int sz = (int) (Math.random() * (reasons.length - 1) + 1);
            ArrayList<String> settlementReason = new ArrayList<>();
            HashSet<Integer> inputReasons = new HashSet<>();
            for (int j = 0; j < sz; j++) {
                int index = random.nextInt(reasons.length);
                if (j == 0) {
                    inputReasons.add(index);
                    settlementReason.add(reasons[index]);
                } else if (inputReasons.add(index)) settlementReason.add(reasons[index]);
            }
            list.add(new HotelBooking(new Person(passportId, surname, name, patronymic, dayOfBirth), settlementDate, evictionDate,
                    new HotelRoom(numberOfRoom, classOfRoom, countOfPlaces), settlementReason));
        }
        return list;
    }

    public static void generateAndWrite() {
        List<HotelBooking> list = generateList(10);
        new HotelBookingToXML().write(list, HotelBookingGenerator.class.getResource("/gen15.xml").getPath());
    }

    public static void main(String[] args) {
        generateAndWrite();
    }
    public static ArrayList<HotelBooking> generator(Scanner scanner, PrintStream printStream) {
        printStream.println("Enter count of elements to generate");
        return generateList(HotelBookingInput.inInt(scanner));
    }
}
