package com.example.financetracker.domain;

public enum FilterType {
    CATEGORY("Category"),
    AMOUNT("Amount"),
    DATE("Date");

    private final String value;

    FilterType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
