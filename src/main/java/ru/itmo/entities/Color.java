package ru.itmo.entities;

public enum Color {
    BLACK,
    ORANGE,
    WHITE,
    BROWN;

    /**
     * @return Строка со всеми элементами enum'а через запятую.
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Color color_type : values()) {
            nameList.append(color_type.name()).append(", ");
        }
        return nameList.substring(0, nameList.length()-2);
    }
}

