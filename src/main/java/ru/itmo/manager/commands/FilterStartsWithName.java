package ru.itmo.manager.commands;

import ru.itmo.entities.StudyGroup;
import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.exceptions.EmptyValueException;
import ru.itmo.exceptions.InvalidNumberOfElementsException;
import ru.itmo.utility.Console;

import java.util.Objects;

public class FilterStartsWithName extends Command {
    private final Console console;
    private final GroupCollection groupCollection;

    public FilterStartsWithName(Console console, GroupCollection groupCollection) {
        super("filter_starts_with_name <name>", "Выводит элементы равные имени");
        this.console = console;
        this.groupCollection = groupCollection;
    }


    @Override
    public void execute(String[] args) {
        try {
            if (args[1].isEmpty()) throw new InvalidNumberOfElementsException();
            if (groupCollection.collectionSize() == 0) throw new EmptyValueException();
            var name = args[1];
            for(StudyGroup group : groupCollection.getStudyGroups()){
                if(Objects.equals(group.getName(), name)) System.out.println(name);
            }

        } catch (EmptyValueException e) {
            throw new RuntimeException(e);
        } catch (InvalidNumberOfElementsException e) {
            throw new RuntimeException(e);
        }

    }
}