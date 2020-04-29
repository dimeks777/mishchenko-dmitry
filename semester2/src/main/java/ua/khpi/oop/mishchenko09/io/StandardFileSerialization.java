package ua.khpi.oop.mishchenko09.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.container.ForwardLinkedList;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.io.*;


public class StandardFileSerialization {
    private static final Logger logger = LoggerFactory.getLogger(StandardFileSerialization.class);

    public void write(ForwardLinkedList<HotelBooking> forwardLinkedList, String path) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeObject(forwardLinkedList);
            objectOutputStream.close();
            logger.info("Write successful");
        } catch (IOException e) {
            logger.error("Error while writing {}", e.getMessage());
        }

    }

    @SuppressWarnings("unchecked")
    public ForwardLinkedList<HotelBooking> read(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ForwardLinkedList<HotelBooking> list = (ForwardLinkedList<HotelBooking>) objectInputStream.readObject();
            objectInputStream.close();
            logger.info("Read successful");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error while reading {}", e.getMessage());
        }
        return null;
    }
}
