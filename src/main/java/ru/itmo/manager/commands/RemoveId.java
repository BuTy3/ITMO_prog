package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.exceptions.EmptyValueException;
import ru.itmo.exceptions.InvalidNumberOfElementsException;
import ru.itmo.exceptions.NotFoundException;
import ru.itmo.utility.Console;

public class RemoveId extends Command {
    private final Console console;
    private final GroupCollection groupCollection;

    /**
     * Конструктор для создания экземпляра команды Remove.
     *
     * @param console         объект для взаимодействия с консолью
     * @param groupCollection менеджер коллекции
     */
    public RemoveId(Console console, GroupCollection groupCollection) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.console = console;
        this.groupCollection = groupCollection;
    }

    @Override
    public void execute(String[] arguments) {
        try {
            if (arguments.length < 2 || arguments[1].isEmpty()) throw new InvalidNumberOfElementsException();
            if (groupCollection.collectionSize() == 0) throw new EmptyValueException();

            int id = Integer.parseInt(arguments[1]);
            var productToRemove = groupCollection.byId(id);
            if (productToRemove == null) throw new NotFoundException();

            groupCollection.remove(productToRemove);
            console.println("Элемент успешно удален.");
            return;
        } catch (EmptyValueException e) {
            throw new RuntimeException(e);
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvalidNumberOfElementsException e) {
            throw new RuntimeException(e);
        }
    }
}