package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.utility.Console;

import java.time.LocalDateTime;

public class Info extends Command {
    private final Console console;
    private final GroupCollection groupCollection;

    /**
     * Конструктор для создания экземпляра команды Info.
     *
     * @param console объект для взаимодействия с консолью
     * @param groupCollection менеджер коллекции
     */
    public Info(Console console, GroupCollection groupCollection) {
        super("info", "вывести информацию о коллекции");
        this.console = console;
        this.groupCollection = groupCollection;
    }

    /**
     * Выполняет команду
     *
     * @param arguments аргументы команды
     */
    @Override
    public void execute(String[] arguments) {
        if (arguments.length > 1 && !arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
        }

        LocalDateTime LastSaveTime = groupCollection.getLastSaveTime();
        String LastSaveTimeString = (LastSaveTime == null) ? "в данной сессии сохранения еще не происходило" :
                LastSaveTime.toLocalDate().toString() + " " + LastSaveTime.toLocalTime().toString();

        console.println("Сведения о коллекции:");
        console.println(" Тип: " + groupCollection.collectionType());
        console.println(" Количество элементов: " + groupCollection.collectionSize());
        console.println(" Дата последнего сохранения:" + LastSaveTimeString);
        console.println(" Дата инициализации:" + groupCollection.getInitializationDate());
    }
}
