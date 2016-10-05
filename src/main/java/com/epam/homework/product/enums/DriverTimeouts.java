package com.epam.homework.product.enums;

import java.util.concurrent.TimeUnit;

public enum DriverTimeouts {

    IMPLICIT_WAIT(10, TimeUnit.SECONDS),
    PAGE_LOAD(60, TimeUnit.SECONDS),
    EXPLICIT_WAIT(30, TimeUnit.SECONDS);


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
