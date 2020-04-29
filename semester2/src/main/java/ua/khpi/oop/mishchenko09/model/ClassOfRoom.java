package ua.khpi.oop.mishchenko09.model;

public enum ClassOfRoom {
    ECONOM("econom"), DEFAULT("default"), LUX("lux");
    private final String numberClass;

    ClassOfRoom(String numberClass) {
        this.numberClass = numberClass;
    }

    public String getNumberClass() {
        return numberClass;
    }
}
