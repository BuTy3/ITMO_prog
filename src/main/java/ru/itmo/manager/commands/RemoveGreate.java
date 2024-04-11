package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.exceptions.*;
import ru.itmo.utility.Console;
import ru.itmo.utility.builders.StudyGroupBuilder;

public class RemoveGreate extends Command {
    private final Console console;
    private final GroupCollection groupCollection;

    public RemoveGreate(Console console, GroupCollection groupCollection) {
        super("remove_greater {element}", "удалить из коллекции все элементы больше заданного");
        this.console = console;
        this.groupCollection = groupCollection;
    }

    @Override
    public void execute(String[] args) {
        try {
            if (!args[1].isEmpty()) throw new EmptyValueException();
            var item = StudyGroupBuilder.build();
            if(!item.validate()) throw new InvalidFormException();

            for(var group : groupCollection.getStudyGroups()){
                if(item.compareTo(group) > 0) groupCollection.remove(group);
            }
            console.println("Элементы успешно удалены!");
        } catch (InvalidFormException exception) {
            console.printError("Поля группы не валидны! Дракон не создан!");
        } catch (EmptyValueException e) {
            throw new RuntimeException(e);
        }
    }
}
