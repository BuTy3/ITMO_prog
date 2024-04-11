package ru.itmo.manager.commands;

import ru.itmo.entities.StudyGroup;
import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.utility.builders.StudyGroupBuilder;

public class AddCommand extends Command {

    public AddCommand(GroupCollection groupCollection) {
        super("add", "Добавляет новую группу в коллекцию", groupCollection);
    }

    @Override
    public void execute(String[] args) {
        try {
            StudyGroup group = StudyGroupBuilder.build(); // Билд запрашивает данные у пользователя и создает объект
            if (group.validate()) {
                groupCollection.add(group);
                System.out.println("Группа успешно добавлена.");
            } else {
                System.out.println("Невозможно добавить группу: некорректные данные.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка при добавлении группы: " + e.getMessage());
        }
    }
}
