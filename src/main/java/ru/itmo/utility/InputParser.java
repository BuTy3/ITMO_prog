package ru.itmo.utility;

import ru.itmo.utility.adapters.LocalDateTimeAdapter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class InputParser {
    private static final Scanner scanner = new Scanner(System.in);

    public static String requestString(String prompt, boolean allowNull) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!allowNull && input.isEmpty()) {
                System.out.println("Это поле не может быть пустым. Пожалуйста, попробуйте снова.");
            } else {
                return input.isEmpty() ? null : input;
            }
        }
    }

    public static int requestInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введено не число. Пожалуйста, попробуйте снова.");
            }
        }
    }
    public static int requestInt(String prompt, int maxVal) {
        while (true) {
            try {
                System.out.print(prompt);
                int x = Integer.parseInt(scanner.nextLine().trim());
                if(x <= maxVal) return x;
            } catch (NumberFormatException e) {
                System.out.println("Введите число не превышающее: " + maxVal);
            }
        }
    }

    public static double requestDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введено не число. Пожалуйста, попробуйте снова.");
            }
        }
    }
    // Добавляем новый метод для запроса long
    public static long requestLong(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Введено не число. Пожалуйста, попробуйте снова.");
            }
        }
    }
    public static long requestLong(String prompt, long maxVal) {
        while (true) {
            try {
                System.out.print(prompt);
                long x = Long.parseLong(scanner.nextLine().trim());
                if(x <= maxVal) return x;
            } catch (NumberFormatException e) {
                System.out.println("Введите число не превышающее: " + maxVal);
            }
        }
    }

    public static int requestInt(int minVal, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int x = Integer.parseInt(scanner.nextLine().trim());
                if(x > minVal) return x;
            } catch (NumberFormatException e) {
                System.out.println("Введите число больше " + minVal);
            }
        }
    }
    public static LocalDateTime requestLocalDate(String prompt) {
        Scanner scanner = new Scanner(System.in);
        LocalDate date = null;
        boolean validInput = false;

        // Определяем формат даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        while (!validInput) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();

                // Парсим строку в LocalDate
                date = LocalDate.parse(input, formatter);
                validInput = true; // Если парсинг прошел успешно, устанавливаем флаг в true
            } catch (Exception e) {
                System.out.println("Некорректный формат даты. Пожалуйста, повторите ввод.");
            }
        }

        return date.atStartOfDay();
    }

    public static String requestString(String prompt, boolean allowNull, int maxLength) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!allowNull && input.isEmpty()) {
                System.out.println("Это поле не может быть пустым. Пожалуйста, попробуйте снова.");
            } else {
                if(input.length() > maxLength){
                    System.out.println("Длина строки не должна превышать " + maxLength);
                    continue;
                }
                return input.isEmpty() ? null : input;
            }
        }
    }
}
