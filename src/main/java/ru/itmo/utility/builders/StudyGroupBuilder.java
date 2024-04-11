package ru.itmo.utility.builders;

import ru.itmo.entities.*;
import ru.itmo.utility.InputParser;

public class StudyGroupBuilder {
    public static StudyGroup build(){
        String name = InputParser.requestString("Введите название группы:", false);
        Coordinates coordinates = CoordinatesBuilder.build();
        int studentsCount = InputParser.requestInt(0, "Введите число студентов: ");
        FormOfEducation form;
        while(true) {
            try {
                System.out.println("Доступные типы: " + FormOfEducation.names());
                form = FormOfEducation.valueOf(InputParser.requestString("Введите форму обучения: ", true).toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверно указан тип. Доступны только: " + FormOfEducation.names());
            }
        }
        Semester semester;
        while(true) {
            try {
                System.out.println("Доступные семестры: " + Semester.names());
                semester = Semester.valueOf(InputParser.requestString("Введите семестр: ", true).toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Неверно указан семестр. Доступны только: " + FormOfEducation.names());
            }
        }
        Person person = PersonBuilder.build();
        return new StudyGroup(name, coordinates, studentsCount, form, semester, person);
    }
}
