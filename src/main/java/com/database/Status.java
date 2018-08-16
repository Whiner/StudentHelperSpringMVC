package com.database;

public enum Status {
    DONE,
    IN_PROCESS,
    NOT_STARTED;

    private static final String DONE_R = "Готова";
    private static final String IN_PROCESS_R = "В процессе";
    private static final String NOT_STARTED_R = "Не начата";

    public static Status valueFromRussian(String name) {
        switch (name) {
            case DONE_R:
                return DONE;
            case IN_PROCESS_R:
                return IN_PROCESS;
            case NOT_STARTED_R:
                return NOT_STARTED;
            default:
                return null;
        }
    }

    public static String toRussian(Status status) {
        switch (status) {
            case DONE:
                return DONE_R;
            case IN_PROCESS:
                return IN_PROCESS_R;
            case NOT_STARTED:
                return NOT_STARTED_R;
            default:
                return null;
        }
    }
}
