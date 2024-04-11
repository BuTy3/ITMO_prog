package ru.itmo.entities;

import ru.itmo.utility.Validatable;

import java.time.LocalDateTime;

public class Person implements Validatable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private LocalDateTime birthday; //Поле может быть null
    private long weight; //Значение поля должно быть больше 0
    private String passportID; //Строка не может быть пустой, Длина строки не должна быть больше 24, Поле не может быть null
    private Color eyeColor; //Поле не может быть null

    public Person(String name, LocalDateTime birthday, long weight, String passportID, Color eyeColor) {
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
        this.eyeColor = eyeColor;
    }

    @Override
    public String toString() {
        return "StudyGroup{\"name\": " + name + ", " +
                "\"birthday\": \"" + birthday + "\", " +
                "\"weight\": \"" + weight + "\", " +
                "\"passportID\": \"" + passportID + "\", " +
                "\"eyeColor\": " + eyeColor +  "\" " + "}";
    }

    @Override
    public boolean validate() {
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (passportID == null || passportID.isEmpty() || passportID.length() > 24) {
            return false;
        }
        if (weight <= 0) {
            return false;
        }
        if (eyeColor == null) {
            return false;
        }
        return true;
    }
}