package ru.itmo.manager.commands;

import ru.itmo.entities.StudyGroup;
import ru.itmo.entities.controller.GroupCollection;

public class ShowCommand extends Command {
    public ShowCommand(GroupCollection groupCollection) {
        super("show", "Выводит все элементы коллекции", groupCollection);
    }

    @Override
    public void execute(String[] args) {
        if (groupCollection.getStudyGroups().isEmpty()) {
            System.out.println("Коллекция пуста.");
        } else {
            System.out.println("Элементы коллекции:");
            for (StudyGroup group : groupCollection.getStudyGroups()) {
                System.out.println(group);
            }
        }
    }
}
