package ru.itmo.manager.commands;


import ru.itmo.exceptions.ScriptRecursionException;
import ru.itmo.manager.CommandInvoker;
import ru.itmo.utility.Console;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 * Команда 'execute_script'. Выполнить скрипт из файла.
 *
 * @author BuTу3
 */
public class ExecuteScript extends Command {
    private final Console console;
    private final Set<String> scriptSet = new HashSet<>();
    private final CommandInvoker commandInvoker;

    public ExecuteScript(Console console, CommandInvoker commandInvoker) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.console = console;
        this.commandInvoker = commandInvoker;
    }

    /**
     * Выполняет команду
     *
     * @return Успешность выполнения команды.
     */
    @Override
    public boolean execute(String[] arguments) {
        if (arguments[1].isEmpty()) {
            console.println("Использование: '" + getName() + "'");
            return false;
        }

        console.println("Выполнение скрипта '" + arguments[1] + "'...");
        if(run(arguments[1]) == ExecuteStatus.OK) return true;
        return false;
    }

    public ExecuteStatus run(String argument) {
        scriptSet.add(argument);
        if (!new File(argument).exists()) {
            argument = "../" + argument;
        }

        String[] userCommand;
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();

            do {
                userCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                console.println(console.getPrompt() + String.join(" ", userCommand));
                if (userCommand[0].equals("execute_script")) {
                    if (scriptSet.contains(userCommand[1])) throw new ScriptRecursionException();
                }
                ExecuteStatus commandStatus = executeCommand(userCommand);
                if (commandStatus != ExecuteStatus.OK) return commandStatus;
            } while (scriptScanner.hasNextLine());

        } catch (NoSuchElementException | IllegalStateException exception) {
            console.printError("Невозможно прочитать данные из файла");
            return ExecuteStatus.ERROR;
        } catch (FileNotFoundException exception) {
            console.printError("Файл не найден");
            return ExecuteStatus.ERROR;
        } catch (ScriptRecursionException exception) {
            console.printError("Обнаружена рекурсия");
            return ExecuteStatus.ERROR;
        } finally {
            scriptSet.remove(argument);
        }
        return ExecuteStatus.OK;
    }

    private ExecuteStatus executeCommand(String[] userCommand) {
        if (userCommand[0].isEmpty()) return ExecuteStatus.OK;
        var command = commandInvoker.getCommands().get(userCommand[0]);

        if (command == null) throw new NoSuchElementException();

        switch (userCommand[0]) {
            case "exit" -> {
                if (!command.execute(userCommand)) return ExecuteStatus.ERROR;
                else return ExecuteStatus.EXIT;
            }
            default -> {
                if (!command.execute(userCommand)) return ExecuteStatus.ERROR;
                return ExecuteStatus.OK;
            }
        }
    }
}