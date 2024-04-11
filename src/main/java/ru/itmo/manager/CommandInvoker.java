package ru.itmo.manager;

import java.util.*;

import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.manager.commands.*;
import ru.itmo.utility.Console;

import static ru.itmo.main.Main.console;
//import ru.itmo.main.Main.console

// Предполагается, что вы имеете соответствующие классы команд
public class CommandInvoker {
    private final Map<String, Command> commandMap = new HashMap<>();
    private final Scanner scanner = new Scanner(System.in);
    private final GroupCollection groupCollection;
    // Предполагается наличие DumpManager для команды Save
    private final DumpManager dumpManager;
    private final List<String[]> commandHistory = new ArrayList<>();

    public CommandInvoker(GroupCollection groupCollection, DumpManager dumpManager) {
        this.groupCollection = groupCollection;
        this.dumpManager = dumpManager;

        // Инициализация команд с передачей всех необходимых зависимостей
        register("show", new ShowCommand(groupCollection));
        register("add", new AddCommand(groupCollection));
        register("clear", new ClearCommand(groupCollection));
        register("help", new HelpCommand(console, this));
        register("exit", new Exit(console));
        register("save", new Save(groupCollection, console, dumpManager));
        register("info", new Info(console, groupCollection));
        register("history", new History(console, groupCollection, this));
        register("remove_at", new RemoveAt(console, groupCollection));
        register("remove_by_id", new RemoveId(console, groupCollection));
        register("update", new Update(console, groupCollection));
        register("remove_by_students_count", new RemoveAllByStudentsCount(console, groupCollection));
        register("filter_starts_with_name", new FilterStartsWithName(console, groupCollection));

    }

    private void register(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String[] args) {
        commandHistory.add(args);
        if (args.length == 0) {
            System.out.println("Не указана команда. Введите 'help' для получения списка команд.");
            return;
        }

        Command command = commandMap.get(args[0].toLowerCase());
        if (command != null) {
            command.execute(args);
        } else {
            System.out.println("Неизвестная команда '" + args[0] + "'. Введите 'help' для получения списка команд.");
        }
    }

    public Map<String, Command> getCommands() {
        return commandMap;
    }

    public List<String[]> getCommandHistory() {
        return commandHistory;
    }
}
