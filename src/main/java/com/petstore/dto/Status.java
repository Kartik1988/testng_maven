package com.petstore.dto;




public enum Status {

    available("available1"),
    pending("pending"),
    sold("sold");

    private String value;

    Status(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
