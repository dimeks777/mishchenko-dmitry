package ua.khpi.oop.mishchenko09.model;

import java.io.Serializable;
import java.util.Objects;

public class HotelRoom implements Comparable<HotelRoom>, Serializable {

    private static final long serialVersionUID = 1L;
    private int numberOfRoom;
    private ClassOfRoom classOfRoom;
    private int countOfPlaces;

    public HotelRoom() {

    }

    public HotelRoom(int numberOfRoom, ClassOfRoom classOfRoom, int countOfPlaces) {
        this.numberOfRoom = numberOfRoom;
        this.classOfRoom = classOfRoom;
        this.countOfPlaces = countOfPlaces;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }

    public void setNumberOfRoom(int numberOfRoom) {
        this.numberOfRoom = numberOfRoom;
    }

    public ClassOfRoom getClassOfRoom() {
        return classOfRoom;
    }

    public void setClassOfRoom(ClassOfRoom classOfRoom) {
        this.classOfRoom = classOfRoom;
    }

    public int getCountOfPlaces() {
        return countOfPlaces;
    }

    public void setCountOfPlaces(int countOfPlaces) {
        this.countOfPlaces = countOfPlaces;
    }

    @Override
    public int compareTo(HotelRoom o) {
        return Integer.compare(this.countOfPlaces, o.countOfPlaces);
    }

    @Override
    public String toString() {
        return "Number of room: " + this.numberOfRoom + "\n"
                + "Class of room: " + this.classOfRoom.getNumberClass() + "\n"
                + "Count of places: " + this.countOfPlaces;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + Objects.hashCode(this);
        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HotelRoom that = (HotelRoom) o;
        return this.numberOfRoom == that.numberOfRoom &&
                this.classOfRoom.equals(that.classOfRoom)
                && this.countOfPlaces == that.countOfPlaces;
    }

}
