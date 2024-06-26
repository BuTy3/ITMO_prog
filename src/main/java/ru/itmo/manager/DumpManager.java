package ru.itmo.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import ru.itmo.utility.Console;

/**
 * Менеджер для работы с файлом, в который происходит сохранение и извлечение коллекции.
 *
 * @param <T> Тип элементов коллекции.
 */
public class DumpManager<T> {
    private final java.lang.reflect.Type collectionType;
    private final String collectionName;
    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new DateTypeAdapter())
            .create();

    private final String fileName;
    private final Console console;

    /**
     * Конструктор для создания экземпляра менеджера.
     *
     * @param fileName Имя файла.
     * @param console  Объект для взаимодействия с консолью.
     * @param clazz    Класс элементов коллекции.
     */
    public DumpManager(String fileName, Console console, Class<T> clazz) {
        this.fileName = fileName;
        this.console = console;
        collectionType = TypeToken.getParameterized(LinkedList.class, clazz).getType();
        String[] parts = clazz.getName().split("\\.");
        collectionName = parts[parts.length - 1];
    }

    /**
     * Записывает коллекцию в файл.
     *
     * @param collection Коллекция.
     */
    public void writeCollection(Collection<T> collection) {
        try (OutputStreamWriter collectionPrintWriter = new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8)) {
            collectionPrintWriter.write(gson.toJson(collection));
            console.println("Коллекция " + collectionName + " сохранена в файл!");
        } catch (IOException exception) {
            console.printError("Загрузочный файл не может быть открыт!");
        }
    }

    /**
     * Считывает коллекцию из файла.
     *
     * @return Считанная коллекция.
     */
    public Collection<T> readCollection() {
        if (fileName != null && !fileName.isEmpty()) {
            try (InputStreamReader fileReader = new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8);
                 BufferedReader reader = new BufferedReader(fileReader)) {

                var jsonString = new StringBuilder();

                String line;
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.isEmpty()) {
                        jsonString.append(line);
                    }
                }

                if (jsonString.isEmpty()) {
                    jsonString = new StringBuilder("[]");
                }

                LinkedList<T> collection = gson.fromJson(jsonString.toString(),
                        collectionType);

                console.println("Коллекция " + collectionName + " успешно загружена!");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                console.printError("Загрузочный файл пуст!");
            } catch (JsonParseException exception) {
                console.printError("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IllegalStateException | IOException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            console.printError("Аргумент командной строки с загрузочным файлом не найден!");
        }
        return new LinkedList<>();
    }

    /**
     * Очищает содержимое файла.
     */
    public void clearFile() {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("");
            writer.close();
            console.println("Файл " + fileName + " успешно очищен!");
        } catch (IOException exception) {
            console.printError("Ошибка при очистке файла " + fileName + ": " + exception.getMessage());
        }
    }

    /**
     * Получает объект для взаимодействия с консолью.
     *
     * @return Объект для взаимодействия с консолью.
     */
    public Console getConsole() {
        return console;
    }
}
