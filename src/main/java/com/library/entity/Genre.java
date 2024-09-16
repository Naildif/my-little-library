package com.library.entity;

public enum Genre {
    DYSTOPIAN,
    CRITICAL_POLITICS,
    ROMANCE,
    MYSTERY,
    SCIENCE_FICTION,
    BIOGRAPHY,
    HISTORY,
    EDUCATIONAL;
    public static String[] getGenres() {
        return new String[] {
                DYSTOPIAN.name(),
                CRITICAL_POLITICS.name(),
                ROMANCE.name(),
                MYSTERY.name(),
                SCIENCE_FICTION.name(),
                BIOGRAPHY.name(),
                HISTORY.name(),
                EDUCATIONAL.name()
        };
    }
}
