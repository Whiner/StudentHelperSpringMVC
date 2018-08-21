package com.database.entites;

import com.database.other.GregorianCalendar;

import javax.persistence.*;

@Entity(name = "notes")
public class StudentWorkNote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private GregorianCalendar date;
    private String note;

    @ManyToOne
    @JoinColumn(name = "info_id", nullable = false)
    private StudentWorkInfo info;

    public Long getId() {
        return id;
    }


    public StudentWorkInfo getInfo() {
        return info;
    }

    public void setInfo(StudentWorkInfo info) {
        this.info = info;
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
