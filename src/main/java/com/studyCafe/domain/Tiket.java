package com.studyCafe.domain;

public enum Tiket {
    defaultHour(0),
    twoHour(2),
    fourHour(4);

    private final int hour;

    Tiket(int hour) {
        this.hour = hour;
    }

    public int getHour() {
        return hour;
    }
}