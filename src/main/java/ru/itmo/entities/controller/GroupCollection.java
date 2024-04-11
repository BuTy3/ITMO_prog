package ru.itmo.entities.controller;

import ru.itmo.entities.StudyGroup;
import ru.itmo.exceptions.DuplicateException;
import ru.itmo.manager.DumpManager;
import ru.itmo.utility.Console;
import ru.itmo.utility.StandardConsole;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

public class GroupCollection {
    private static final ArrayList<StudyGroup> groups = new ArrayList<>();
    private final LocalDateTime initializationDate;
    private LocalDateTime lastSaveTime;
    private DumpManager<StudyGroup> dumpManager;

    public GroupCollection() {
        this.initializationDate = LocalDateTime.now();
        lastSaveTime = LocalDateTime.now();
    }
    public GroupCollection(DumpManager<StudyGroup> dumpManager){
        this();
        this.dumpManager = dumpManager;
        loadCollection();
        touchNextId();
    }
    public GroupCollection(String filePath){
        this(new DumpManager<StudyGroup>(filePath, new StandardConsole(), StudyGroup.class));
    }

    private void loadCollection() {
        Collection<StudyGroup> loadedGroups = dumpManager.readCollection();
        try {
            for (StudyGroup item : loadedGroups) {
                if (item != null) {
                    int id = item.getId();
                    StudyGroup existingItem = byId(id);
                    if (existingItem != null) {
                        throw new DuplicateException();
                    }
                }
                groups.add(item);
            }
            validateAll(dumpManager.getConsole());
        } catch (DuplicateException e) {
            dumpManager.getConsole().printError("Ошибка загрузки коллекции: обнаружены дубликаты StudyGroup по полю id, загружены только первые значения.");
        }
    }

    public void validateAll(Console console) {
        AtomicBoolean flag = new AtomicBoolean(true);
        groups.forEach(group -> {
            if (!group.validate()) {
                console.printError("Группа с id=" + group.getId() + " имеет недопустимые поля.");
                flag.set(false);
            }
        });
        if (flag.get()) {
            console.println("! Загруженные данные валидны.");
        }
    }

    public LocalDateTime getInitializationDate() {
        return initializationDate;
    }

    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    public DumpManager<StudyGroup> getDumpManager() {
        return dumpManager;
    }

    public static void touchNextId() {
        long maxGroupID = 1;
        for(StudyGroup group : groups){
            if (group.getId() > maxGroupID){
                maxGroupID = group.getId();
            }
        }
        StudyGroup.updateNextID(++maxGroupID);
    }

    public boolean add(StudyGroup group) {
        return groups.add(group);
    }

    public ArrayList<StudyGroup> getStudyGroups() {
        return groups;
    }

    public String collectionType() { return groups.getClass().getName(); }

    public int collectionSize() { return groups.size(); }

    public StudyGroup byId(long id) {
        if (groups.isEmpty()) return null;
        return groups.stream()
                .filter(group -> group.getId() == id)
                .findFirst()
                .orElse(null);
    }
    public boolean remove(long id){
        return groups.remove(id);
    }
    public void remove(StudyGroup productToRemove) {
        groups.remove(productToRemove);
    }


}
