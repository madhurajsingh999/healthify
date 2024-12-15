package com.healthify.entity;

public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE"),
    TRANSGENDER("TRANSGENDER");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

}
