package com.epam.homework.enums;

import java.util.concurrent.TimeUnit;

/**
 * Created by Al on 26.09.2016.
 */
public enum DriverTimeouts {

    IMPLICIT_WAIT(10, TimeUnit.SECONDS),
    PAGE_LOAD(60, TimeUnit.SECONDS);

    private int value;
    private TimeUnit unit;


    DriverTimeouts(int value, TimeUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public int getValue() {
        return value;
    }
}
