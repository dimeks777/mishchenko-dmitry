package ua.khpi.oop.mishchenko09.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Person implements Comparable<Person>, Serializable {

    private static final long serialVersionUID = 1L;
    private String passportId;
    private String surname;
    private String name;
    private String patronymic;
    private LocalDate dayOfBirth;
    public static final DateTimeFormatter DAY_OF_BIRTH_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public Person() {
    }

    public Person(String passportId, String surname, String name,
                  String patronymic, LocalDate dayOfBirth) {
        this.passportId = passportId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.dayOfBirth = dayOfBirth;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(LocalDate dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    @Override
    public int compareTo(Person o) {
        return this.dayOfBirth.compareTo(o.dayOfBirth);
    }

    @Override
    public String toString() {
        return "Passport ID: " + this.passportId + "\n" + "Surname: " + this.surname + "\n"
                + "Name: " + this.name + "\n" + "Patronymic: "
                + this.patronymic + "\n" + "Day of birth: " + this.dayOfBirth.format(DAY_OF_BIRTH_FORMAT);
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
        Person that = (Person) o;
        return this.passportId.equals(that.passportId) && this.surname.equals(that.surname)
                && this.name.equals(that.name) && this.patronymic.equals(that.patronymic)
                && this.dayOfBirth.equals(that.dayOfBirth);
    }

}
