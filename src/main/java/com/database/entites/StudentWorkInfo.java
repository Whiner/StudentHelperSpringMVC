package com.database.entites;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "info")
public class StudentWorkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String manualPath;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudentWorkNote> notes = new HashSet<>();

    @OneToOne(mappedBy = "info")
    private StudentWork work;

    public StudentWork getWork() {
        return work;
    }

    public void setWork(StudentWork work) {
        this.work = work;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManualPath() {
        return manualPath;
    }

    public void setManualPath(String manualPath) {
        this.manualPath = manualPath;
    }

    public Set<StudentWorkNote> getNotes() {
        return notes;
    }

    public void setNotes(Set<StudentWorkNote> notes) {
        this.notes = notes;
    }
}
