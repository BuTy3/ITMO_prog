package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.manager.DumpManager;
import ru.itmo.utility.Console;

import java.util.Collection;

public class Save extends Command {
    private final Console console;
    private final DumpManager dumpManager;

    /**
     * Конструктор для создания экземпляра команды Save.
     *
     * @param console объект для взаимодействия с консолью
     * @param dumpManager менеджер коллекции
     */
    public Save(GroupCollection groupCollection, Console console, DumpManager dumpManager) {
        super("save", "сохранить коллекции в файлы");
        this.console = console;
        this.dumpManager = dumpManager;
        this.groupCollection = groupCollection;
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     */
    @Override
    public void execute(String[] arguments) {
        if (!arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
        }

        dumpManager.writeCollection(groupCollection.getStudyGroups());
    }
}