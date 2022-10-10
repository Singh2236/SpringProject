package com.navi.modelSchool.model;

import lombok.Data;

@Data
public class Holiday extends BaseEntity {
    public String day;
    public String reason;
    public Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}
