package ru.itmo.entities;

import ru.itmo.utility.Validatable;

public class Coordinates implements Validatable {
    private long x; //Максимальное значение поля: 964
    private Integer y; //Максимальное значение поля: 751, Поле не может быть null

    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ";" + y;
    }

    @Override
    public boolean validate() {
        if (y == null) {
            return false;
        }
        if (x > 964) {
            return false;
        }
        if (y > 751) {
            return false;
        }
        return true;
    }
}
