package ru.itmo.utility.builders;

import ru.itmo.entities.Color;
import ru.itmo.entities.Person;
import ru.itmo.utility.InputParser;

import java.time.LocalDateTime;

public class PersonBuilder {
    public static Person build(){
        System.out.println("Добавление админа группы.");
        String name = InputParser.requestString("Введите имя админа(пустую строку для пропуска создания): ", true);
        if(name == null) return null;
        LocalDateTime birthday = InputParser.requestLocalDate("Введите дату рождения в формате yyyy-MM-dd: ");
        long weight = InputParser.requestLong("Введите вес: ");
        String passportID = InputParser.requestString("Введите PassportID: ", false, 24);
        Color color;
        while(true) {
            try {
                System.out.println("Доступные цвета: " + Color.names());
                color = Color.valueOf(InputParser.requestString("Введите цвет волос: ", false).toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверно указан цвет. Доступны только: " + Color.names());
            }
        }
        return new Person(name, birthday, weight, passportID, color);
    }
}
