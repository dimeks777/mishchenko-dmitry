package ua.khpi.oop.mishchenko09.model;


import java.beans.XMLEncoder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class HotelBooking implements Comparable<HotelBooking>, Serializable {

    private static final long serialVersionUID = 1L;
    private Person person;
    private LocalDate settlementDate;
    private LocalDate evictionDate;
    private HotelRoom hotelRoom;
    private ArrayList<String> settlementReasons;

    public HotelBooking() {

    }

    public HotelBooking(Person person, LocalDate settlementDate, LocalDate evictionDate, HotelRoom hotelRoom, ArrayList<String> settlementReasons) {
        this.person = person;
        this.settlementDate = settlementDate;
        this.evictionDate = evictionDate;
        this.hotelRoom = hotelRoom;
        this.settlementReasons = settlementReasons;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public LocalDate getEvictionDate() {
        return evictionDate;
    }

    public void setEvictionDate(LocalDate evictionDate) {
        this.evictionDate = evictionDate;
    }

    public HotelRoom getHotelRoom() {
        return hotelRoom;
    }

    public void setHotelRoom(HotelRoom hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    public ArrayList<String> getSettlementReasons() {
        return settlementReasons;
    }

    public void setSettlementReasons(ArrayList<String> settlementReasons) {
        this.settlementReasons = settlementReasons;
    }

    @Override
    public int compareTo(HotelBooking o) {
        return Duration.between(this.settlementDate, this.evictionDate).compareTo(Duration.between(o.settlementDate, o.evictionDate));
    }

    @Override
    public String toString() {
        return "\n----------------------------------------------------\n"
                + person + "\n"
                + "Date of settlement: " + settlementDate + "\n"
                + "Date of eviction: " + evictionDate + "\n"
                + hotelRoom + "\n"
                + "Reasons of settlement: \n" + settlementReasons
                + "\n----------------------------------------------------\n";
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
        HotelBooking that = (HotelBooking) o;
        return this.person.equals(that.person)
                && this.settlementDate.equals(that.settlementDate)
                && this.evictionDate.equals(that.evictionDate)
                && this.settlementReasons.equals(that.settlementReasons);
    }

    public boolean checkNotNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) == null)
                return false;
        return true;
    }

    public void saveListXML(XMLEncoder encoder) {
        try {
            encoder.writeObject(settlementReasons);
        } catch (Exception ignored) {

        }
    }
}
