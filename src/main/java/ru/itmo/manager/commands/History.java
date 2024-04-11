package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.manager.CommandInvoker;
import ru.itmo.utility.Console;

public class History extends Command {
    private final Console console;
    private final GroupCollection groupCollection;
    private final CommandInvoker commandInvoker;

    /**
     * Конструктор для создания экземпляра команды Remove.
     *
     * @param console         объект для взаимодействия с консолью
     * @param groupCollection менеджер коллекции
     */
    public History(Console console, GroupCollection groupCollection, CommandInvoker commandInvoker) {
        super("history", "Показывает последние 12 команд");
        this.console = console;
        this.groupCollection = groupCollection;
        this.commandInvoker = commandInvoker;
    }

    @Override
    public void execute(String[] arguments) {
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
        }

        commandInvoker.getCommandHistory().forEach(console::println);
    }
}