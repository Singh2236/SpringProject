package com.navi.modelSchool.model;

public class Holiday {
    public final String day;
    public final String reason;
    public final Type type;

    public enum Type{
        FESTIVAL, FEDERAL
    }

    public Holiday(String day, String reason, Type type) {
        this.day = day;
        this.reason = reason;
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public Type getType() {
        return type;
    }

}
