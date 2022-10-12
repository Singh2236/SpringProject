package com.navi.modelSchool.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "holidays")
public class Holiday extends BaseEntity {

    @Id
    public String day;

    public String reason;

    @Enumerated(EnumType.STRING)
    public Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }

}
