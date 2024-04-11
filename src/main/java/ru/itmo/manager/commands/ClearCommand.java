package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;

public class ClearCommand extends Command {
    public ClearCommand(GroupCollection groupCollection) {
        super("clear", "Очищает коллекцию", groupCollection);
    }

    @Override
    public void execute(String[] args) {
        if (groupCollection != null && !groupCollection.getStudyGroups().isEmpty()) {
            groupCollection.getStudyGroups().clear(); // Очищаем коллекцию
            System.out.println("Коллекция успешно очищена.");
        } else {
            System.out.println("Коллекция уже пуста или не инициализирована.");
        }
    }
}
