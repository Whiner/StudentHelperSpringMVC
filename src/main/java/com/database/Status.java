package com.database;

public enum Status {
    DONE,
    IN_PROCESS,
    NOT_STARTED;

    public static Status valueFromRussian(String name) {
        switch (name) {
            case "Готова":
                return DONE;
            case "В процессе":
                return IN_PROCESS;
            case "Не начата":
                return NOT_STARTED;
            default:
                return null;
        }
    }
}
