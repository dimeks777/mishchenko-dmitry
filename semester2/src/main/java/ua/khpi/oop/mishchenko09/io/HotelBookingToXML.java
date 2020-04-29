package ua.khpi.oop.mishchenko09.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.beans.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class HotelBookingToXML {
    private static final Logger logger = LoggerFactory.getLogger(HotelBookingToXML.class);

    public void write(ForwardLinkedList<HotelBooking> forwardLinkedList, String path) {

        try {
            XMLEncoder encoder = new XMLEncoder(
                    new BufferedOutputStream(
                            new FileOutputStream(path)));

            encoder.setPersistenceDelegate(LocalDate.class,
                    new PersistenceDelegate() {
                        @Override
                        protected Expression instantiate(Object localDate, Encoder encdr) {
                            return new Expression(localDate,
                                    LocalDate.class,
                                    "parse",
                                    new Object[]{localDate.toString()});
                        }
                    });

            for (HotelBooking elem : forwardLinkedList) {
                encoder.writeObject(elem);
                elem.saveListXML(encoder);
            }
            encoder.close();
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        }
    }

    @SuppressWarnings("unchecked")
    public ForwardLinkedList<HotelBooking> read(String path) {
        try {
            boolean test = true;
            XMLDecoder decoder = new XMLDecoder(
                    new BufferedInputStream(
                            new FileInputStream(path)));
            ForwardLinkedList<HotelBooking> list = new ForwardLinkedList<>();
            while (test) {
                try {
                    HotelBooking object = (HotelBooking) decoder.readObject();
                    object.setSettlementReasons((ArrayList<String>) decoder.readObject());
                    if (RegexCheck.checkObject(object)) list.add(object);
                } catch (Exception e) {
                    test = false;
                }
            }
            return list;
        } catch (FileNotFoundException e) {
            logger.error("File not found");
        }
        return null;
    }
}
