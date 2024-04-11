package ru.itmo.utility;


import ru.itmo.entities.StudyGroup;

public abstract class Element implements Comparable<Element>, Validatable {
    abstract public int getId();

    public abstract int compareTo(StudyGroup studyGroup);
}
