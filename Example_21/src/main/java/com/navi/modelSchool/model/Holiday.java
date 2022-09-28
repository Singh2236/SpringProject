package com.navi.modelSchool.model;

import lombok.Data;

@Data
public class Holiday {
    public final String day;
    public final String reason;
    public final Type type;

    public enum Type{
        FESTIVAL, FEDERAL
    }

}
