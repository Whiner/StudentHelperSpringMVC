package com.database;

public enum Type {
    LABORATORY,
    TEST,
    PRACTICAL,
    ESSEY,
    COURSE_PROJECT,
    EXAM,
    DIPLOMA;

    public static Type valueFromRussian(String name) {
        switch (name) {
            case "Лабораторная":
                return LABORATORY;
            case "Контрольная":
                return TEST;
            case "Курсовая":
                return COURSE_PROJECT;
            case "Дипломная":
                return DIPLOMA;
            case "Практическая":
                return PRACTICAL;
            case "Экзамен":
                return EXAM;
            default:
                return null;
        }
    }
}
