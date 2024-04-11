package ru.itmo.utility;

import ru.itmo.manager.CommandInvoker;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Runner {
    private final CommandInvoker commandInvoker;
    private Scanner scanner;

    public Runner(CommandInvoker commandInvoker) {
        this.commandInvoker = commandInvoker;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        try {
            System.out.println("Добро пожаловать в консоль управления. Введите 'help' для получения списка команд.");
            String[] userCommand;
            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    throw new NoSuchElementException();
                }
                String input = "";
                input = scanner.nextLine().trim();
                userCommand = (input + " ").split(" ", 2);
                userCommand[1] = userCommand[1].trim();
                if ("exit".equalsIgnoreCase(userCommand[0])) {
                    handleExit();
                }
                try {
                    commandInvoker.executeCommand(userCommand);
                } catch (NoSuchElementException e) {
                    System.out.println("Команда '" + input + "' не найдена. Введите 'help' для помощи");
                }
            }
        } catch (NoSuchElementException | IllegalStateException e) {
            System.err.println("Ошибка ввода. Экстренное завершение программы.");
            safelyExit();
        }
    }

    private void handleExit() {
        commandInvoker.executeCommand(new String[]{"exit"});
    }

    private void safelyExit() {
        commandInvoker.executeCommand(new String[]{"save"});
        commandInvoker.executeCommand(new String[]{"exit"});
    }
}
