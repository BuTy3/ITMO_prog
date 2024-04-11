package ru.itmo.utility.builders;

import ru.itmo.entities.Coordinates;
import ru.itmo.utility.InputParser;

public class CoordinatesBuilder {
    public static Coordinates build(){
        long x = InputParser.requestLong("Введите координату X: ", 964);
        int y = InputParser.requestInt("Введите координату Y: ", 751);
        return new Coordinates(x, y);
    }
}
