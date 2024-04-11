package ru.itmo.manager.commands;


import ru.itmo.utility.Console;

/**
 * Команда 'execute_script'. Выполнить скрипт из файла.
 * @author BuTу3
 */
public class ExecuteScript extends Command {
    private final Console console;

    public ExecuteScript(Console console) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.console = console;
    }

    /**
     * Выполняет команду
     * @return Успешность выполнения команды.
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return;
        }

        console.println("Выполнение скрипта '" + arguments[1] + "'...");
    }
}
