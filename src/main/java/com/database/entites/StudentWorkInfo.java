package com.database.entites;

import javax.persistence.*;
import java.util.*;

@Entity(name = "info")
public class StudentWorkInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String manualPath;

    @OneToMany(mappedBy = "info", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("date DESC")
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

    public Set<StudentWorkNote> sortByDate() {
        List<StudentWorkNote> list = new ArrayList<>(this.notes);
        Collections.sort(list);
        return new HashSet<>(list);
    }
}
