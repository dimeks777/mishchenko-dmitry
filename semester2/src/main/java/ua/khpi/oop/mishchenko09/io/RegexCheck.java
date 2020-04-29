package ua.khpi.oop.mishchenko09.io;

import ua.khpi.oop.mishchenko09.model.HotelBooking;

public class RegexCheck {
    public static boolean checkPassportID(String value) {
        return value.matches("[\\d]{9}|[A-Z]{2}[\\d]{6}");
    }

    public static boolean checkSNP(String value) {
        return value.matches("^[A-Z]{1}[a-z]*");
    }

    public static boolean checkNumberOfRoom(String value) {
        return value.matches("[\\d]{3,4}");
    }

    public static boolean checkCountOfPlaces(String value) {
        return value.matches("[1-9]");
    }

    public static boolean searchByReason(String reason, String filter) {
        return reason.toLowerCase().matches("[\\S\\s]*(" + filter.toLowerCase() + ")[\\S\\s]*");
    }


    public static boolean checkObject(HotelBooking obj) {
        return checkPassportID(obj.getPerson().getPassportId())
                && checkSNP(obj.getPerson().getSurname())
                && checkSNP(obj.getPerson().getName())
                && checkSNP(obj.getPerson().getPatronymic())
                && checkNumberOfRoom(String.valueOf(obj.getHotelRoom().getNumberOfRoom()))
                && checkCountOfPlaces(String.valueOf(obj.getHotelRoom().getCountOfPlaces()))
                && obj.getSettlementReasons() != null;
    }


}
