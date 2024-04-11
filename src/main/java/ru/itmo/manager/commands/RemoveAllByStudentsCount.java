package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.exceptions.*;
import ru.itmo.utility.Console;
import ru.itmo.utility.builders.StudyGroupBuilder;

public class RemoveAllByStudentsCount extends Command {
    private final Console console;
    private final GroupCollection groupCollection;

    /**
     * Конструктор для создания экземпляра команды Remove.
     *
     * @param console         объект для взаимодействия с консолью
     * @param groupCollection менеджер коллекции
     */
    public RemoveAllByStudentsCount(Console console, GroupCollection groupCollection) {
        super("remove_all_by_Students_count <c>", "удалить элемент из коллекции по количеству студентов");
        this.console = console;
        this.groupCollection = groupCollection;
    }

    @Override
    public void execute(String[] arguments) {
        try {
            if (arguments[1].isEmpty()) throw new InvalidNumberOfElementsException();
            if (groupCollection.collectionSize() == 0) throw new EmptyValueException();

            var id = Integer.parseInt(arguments[1]);
            var itemByID = groupCollection.byId(id);
            if (itemByID == null) throw new NotFoundException();

            console.println("* Введите данные обновленной группы:");
            console.prompt();

            var newGroup = StudyGroupBuilder.build();
            itemByID.update(newGroup);

            console.println("Группа успешно обновлена.");
            return;

        } catch (InvalidNumberOfElementsException exception) {
            console.println("Использование: '" + getName() + "'");
        } catch (EmptyValueException exception) {
            console.printError("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            console.printError("ID должен быть представлен числом!");
        } catch (NotFoundException exception) {
            console.printError("Группы с таким ID в коллекции нет!");
        }
    }
    private void removeAllBy(int count){

    }
}