package com.example.liferay.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ClassName_")
public class ClassName {

    @Id
    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "value")
    private String value;

    public Long getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(Long classNameId) {
        this.classNameId = classNameId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
