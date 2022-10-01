package com.studyCafe.domain;

public enum Tiket {
    defaultHour(60),
    twoHour(120),
    fourHour(240);

    private final int hour;

    Tiket(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }
}