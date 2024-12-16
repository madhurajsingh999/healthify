package com.healthify.entity;

public enum Status {
    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    TERMINATED("TERMINATED"),
    DELETE("DELETE");

    private String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}