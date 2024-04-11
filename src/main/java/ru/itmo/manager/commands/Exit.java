package ru.itmo.manager.commands;

import ru.itmo.utility.Console;

public class Exit extends Command {
    private final Console console;

    /**
     * Конструктор для создания экземпляра команды Exit.
     *
     * @param console объект для взаимодействия с консолью
     */
    public Exit(Console console) {
        super("exit", "завершить программу (без сохранения в файл)");
        this.console = console;
    }

    /**
     * Выполняет команду.
     *
     * @param arguments аргументы команды (ожидается отсутствие аргументов)
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
        }

        console.println("Завершение выполнения...");
        System.exit(0);
    }
}
