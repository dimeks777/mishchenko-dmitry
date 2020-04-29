package ua.khpi.oop.mishchenko09.model;

import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.io.HotelBookingToXML;
import ua.khpi.oop.mishchenko09.io.HotelBookingView;
import ua.khpi.oop.mishchenko09.io.View;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class HotelBookingGenerator {

    public static ForwardLinkedList<HotelBooking> generateList(int size) {
        Random random = new Random();
        ForwardLinkedList<HotelBooking> list = new ForwardLinkedList<>();
        String[] classes = {"econom", "default", "lux"};
        String[] reasons = {"travelling", "business trip", "hiding", "business meeting"};
        String[] surnames = {"Smith","Johnson","Williams","Brown","Davis","Miller","Wilson","Moore","Taylor","Anderson","Thomas","Jackson","Harris","Martin","Thompson","Martinez","Robinson","Clark"};
        String[] names = {"Oliver","Jack","Harry","Jacob","Charlie","Thomas","George","Oscar","James","William","John","Robert","Michael","David","Richard","Joseph","Charles","Mason"};
        String[] patronymics = {"Aalbers","Aarts","Babayev","Beatson","Ben-David","Clowers","Davidov","Ellison","Enríquez","Fedorovich","Fernandes","Georgiev","Hermans","Ibragimov","Ivanenko","Jakobsen","Lajovic","Karlson"};
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
            randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
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
            for (int j = 0; j < sz; j++) {
                settlementReason.add(reasons[random.nextInt(reasons.length)]);
            }
            list.add(new HotelBooking(new Person(passportId, surname, name, patronymic, dayOfBirth), settlementDate, evictionDate,
                    new HotelRoom(numberOfRoom, classOfRoom, countOfPlaces), settlementReason));
        }
        return list;
    }

    public static void main(String[] args) {
        ForwardLinkedList<HotelBooking> list = generateList(10000);
        new HotelBookingToXML().write(list,HotelBookingGenerator.class.getResource("/Generated.xml").getPath());
    }
}
