package ru.itmo.manager.commands;

import ru.itmo.manager.CommandInvoker;
import ru.itmo.utility.Console;
import ru.itmo.utility.StandardConsole;

public class HelpCommand extends Command {
    private final Console console;
    private final CommandInvoker сommandInvoker;
    public HelpCommand(StandardConsole console, CommandInvoker сommandInvoker) {
        super("help", "Выводит список команд и их описание");
        this.console = console;
        this.сommandInvoker = сommandInvoker;
    }

    @Override
    public void execute(String[] args) {
        if (!args[1].isEmpty()) {
            System.out.println("Пожалуйста введите команду в правильном формате");
        }
        сommandInvoker.getCommands().values()
                .forEach(command -> console.printTable(command.getName(), command.getDescription()));
    }
}
