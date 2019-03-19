package com.infosupport.ap.exercise.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ClassRegistration {
    @Id
    @GeneratedValue
    private Long id;
    @Length(min = 4)
    private String classname;
    @NotNull
    private String lector;
    private String topic;
    private Boolean closed;

    public ClassRegistration(String classname, String lector, String topic) {
        this.classname = classname;
        this.lector = lector;
        this.topic = topic;
        closed = false;
    }

    public ClassRegistration() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Boolean getClosed() {
        return closed;
    }

    public String getClassname() {
        return classname;
    }

    public String getLector() {
        return lector;
    }

    public String getTopic() {
        return topic;
    }
}
