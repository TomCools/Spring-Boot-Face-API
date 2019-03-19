package com.infosupport.ap.exercise.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Presence {
    @GeneratedValue
    @Id
    private Long id;
    private Long classId;
    private String studentName;
    private Date arrival;

    public Presence(Long classId, String studentName, Date arrival) {
        this.classId = classId;
        this.studentName = studentName;
        this.arrival = arrival;
    }

    public Presence() {
    }

    public Long getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public Long getClassId() {
        return classId;
    }

    public Date getArrival() {
        return arrival;
    }
}
