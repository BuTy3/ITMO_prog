package ru.itmo.main;

import ru.itmo.entities.*;
import ru.itmo.entities.controller.GroupCollection;
import ru.itmo.manager.CommandInvoker;
import ru.itmo.manager.DumpManager;
import ru.itmo.utility.Runner;
import ru.itmo.utility.StandardConsole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final StandardConsole console = new StandardConsole();
    public static void main (String[] args){
        String filePath = System.getenv("GROUPS_FILE_PATH");
        if (filePath == null || filePath.isEmpty()) {
            System.err.println("Переменная среды 'GROUPS_FILE_PATH' не задана или пуста.");
            return; // Завершаем работу приложения, если путь не задан
        }
        DumpManager dumpManager = new DumpManager(filePath, new StandardConsole(), StudyGroup.class);
        GroupCollection groupCollection = new GroupCollection(dumpManager);
        CommandInvoker commandInvoker = new CommandInvoker(groupCollection, dumpManager);
        Runner runner = new Runner(commandInvoker);
        runner.start();//test commit
    }
}
