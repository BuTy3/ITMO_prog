//package ru.itmo.utility;
//
//import ru.itmo.manager.CommandInvoker;
//
//import java.util.NoSuchElementException;
//import java.util.Queue;
//import java.util.Scanner;
//import java.util.Set;
//
//public class Runner {
//    private final CommandInvoker commandInvoker;
//    private Scanner scanner;
//
//    public Runner(CommandInvoker commandInvoker) {
//        this.commandInvoker = commandInvoker;
//        this.scanner = new Scanner(System.in);
//    }
//
//    public void start() {
//        try {
//            System.out.println("Добро пожаловать в консоль управления. Введите 'help' для получения списка команд.");
//            String[] userCommand;
//            while (true) {
//                System.out.print("> ");
//                if (!scanner.hasNextLine()) {
//                    throw new NoSuchElementException();
//                }
//                String input = "";
//                input = scanner.nextLine().trim();
//                userCommand = (input + " ").split(" ", 2);
//                userCommand[1] = userCommand[1].trim();
//                if ("exit".equalsIgnoreCase(userCommand[0])) {
//                    handleExit();
//                } else if ("execute_script".equalsIgnoreCase(userCommand[0])) {
//                    execute(userCommand);
//                    continue;
//                }
//                try {
//                    commandInvoker.executeCommand(userCommand);
//                } catch (NoSuchElementException e) {
//                    System.out.println("Команда '" + input + "' не найдена. Введите 'help' для помощи");
//                }
//            }
//        } catch (NoSuchElementException | IllegalStateException e) {
//            System.err.println("Ошибка ввода. Экстренное завершение программы.");
//            safelyExit();
//        }
//    }
//    private void execute(String[] args){
//        if(args.length != 2) //обработка если не 2 элемента
//
//
//            // через set историю всех файлов запущенный через execute_script
//            try {
//                commandInvoker.executeCommand(args);
//                Scanner scanner1 = new Scanner(args[1]);
//                String[] userCommand;
//                while(scanner1.hasNext()){
//                    String input = scanner1.nextLine();
//                    userCommand = (input + " ").split(" ", 2);
//                    if ("exit".equalsIgnoreCase(userCommand[0])) {
//                        handleExit();
//                    } else if ("execute_script".equalsIgnoreCase(userCommand[0])) {
//                        // добавить в set uscom[1]
//                        // добавить проверку на то существует ли в сет, если да, рекурсия, выдаём ошибку
//                    }
//                    // удалить из set(стека) выполненный файл args[1]
//                }
//                while (true) {
//                    System.out.print("> ");
//                    if (!scanner.hasNextLine()) {
//                        throw new NoSuchElementException();
//                    }
//                    String input = "";
//                    input = scanner.nextLine().trim();
//                    userCommand = (input + " ").split(" ", 2);
//                    userCommand[1] = userCommand[1].trim();
//                    if ("exit".equalsIgnoreCase(userCommand[0])) {
//                        handleExit();
//                    } else if ("execute_script".equalsIgnoreCase(userCommand[0])) {
//                        execute();
//                    }
//                    try {
//                        commandInvoker.executeCommand(userCommand);
//                    } catch (NoSuchElementException e) {
//                        System.out.println("Команда '" + input + "' не найдена. Введите 'help' для помощи");
//                    }
//                }
//            }
//    }
//    private void handleExit() {
//        commandInvoker.executeCommand(new String[]{"exit"});
//    }
//
//    private void safelyExit() {
//        commandInvoker.executeCommand(new String[]{"save"});
//        commandInvoker.executeCommand(new String[]{"exit"});
//    }
//}
