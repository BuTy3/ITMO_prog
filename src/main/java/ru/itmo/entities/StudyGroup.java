package ru.itmo.entities;

import ru.itmo.utility.Element;
import ru.itmo.utility.Validatable;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class StudyGroup extends Element implements Validatable, Serializable {
    private static long nextId = 0;
    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int studentsCount; //Значение поля должно быть больше 0
    private FormOfEducation formOfEducation; //Поле может быть null
    private Semester semesterEnum; //Поле может быть null
    private Person groupAdmin; //Поле может быть null

    public StudyGroup(String name, Coordinates coordinates, int studentsCount, FormOfEducation formOfEducation, Semester semesterEnum, Person groupAdmin) {
        this.id = nextId++;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.studentsCount = studentsCount;
        this.formOfEducation = formOfEducation;
        this.semesterEnum = semesterEnum;
        this.groupAdmin = groupAdmin;
    }

    public static void updateNextID(long newNextID) {
        nextId = newNextID;
    }


    @Override
    public boolean validate() {
        if (id == null || id <= 0) {
            return false;
        }
        if (name == null || name.isEmpty()) {
            return false;
        }
        if (coordinates == null) {
            return false;
        }
        if (creationDate == null) {
            return false;
        }
        if (studentsCount <= 0) {
            return false;
        }
        return true;
    }


    public void update(StudyGroup studyGroup) {
        this.name = studyGroup.name;
        this.coordinates = studyGroup.coordinates;
        this.creationDate = studyGroup.creationDate;
        this.studentsCount = studyGroup.studentsCount;
        this.formOfEducation = studyGroup.formOfEducation;
        this.semesterEnum = studyGroup.semesterEnum;
        this.groupAdmin = studyGroup.groupAdmin;
    }

    public int getId() {
        return Math.toIntExact(id);
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    public static long getNextId() {
        return nextId;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Semester getSemesterEnum() {
        return semesterEnum;
    }

    public Person getGroupAdmin() {
        return groupAdmin;
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        return (int) (this.id - studyGroup.getId());
    }

    @Override
    public int compareTo(Element o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, studentsCount, formOfEducation, semesterEnum, groupAdmin);
    }

    @Override
    public String toString() {
        return "StudyGroup{\"id\": " + id + ", " +
                "\"name\": \"" + name + "\", " +
                "\"creationDate\": \"" + creationDate + "\", " +
                "\"coordinates\": \"" + coordinates + "\", " +
                "\"creationDate\": \"" + creationDate + "\", " +
                "\"studentsCount\": \"" + studentsCount + "\", " +
                "\"formOfEducation\": " + (formOfEducation == null ? "null" : "\"" + formOfEducation + "\"") + ", " +
                "\"semesterEnum\": " + (semesterEnum == null ? "null" : "\"" + semesterEnum + "\"") + ", " +
                "\"groupAdmin\": " + (groupAdmin == null ? "null" : "\"" + groupAdmin + "\"") + "\", " + "}";
    }
}
