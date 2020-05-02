package ua.khpi.oop.mishchenko15.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.khpi.oop.mishchenko09.model.HotelBooking;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class StandardFileSerialization {
    private static final Logger logger = LoggerFactory.getLogger(StandardFileSerialization.class);

    public void write(List<HotelBooking> forwardLinkedList, String path) {
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
    public ArrayList<HotelBooking> read(String path) {
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            ArrayList<HotelBooking> list = (ArrayList<HotelBooking>) objectInputStream.readObject();
            objectInputStream.close();
            logger.info("Read successful");
            return list;
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error while reading {}", e.getMessage());
        }
        return null;
    }
}
