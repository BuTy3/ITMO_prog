package ru.itmo.manager.commands;

import ru.itmo.entities.controller.GroupCollection;

public abstract class Command {
    protected String name;
    protected String description;
    protected GroupCollection groupCollection;

    public Command(String name, String description, GroupCollection groupCollection) {
        this.name = name;
        this.description = description;
        this.groupCollection = groupCollection;
    }

    /**
     * Конструктор для создания команды с именем и описанием.
     *
     * @param name        Название команды.
     * @param description Описание команды.
     */
    public Command(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void execute(String[] args);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
