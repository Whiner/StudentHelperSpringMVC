package com.database;

import javax.persistence.*;

@Entity
public class StudentWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Type type;
    private String discipline;
    private Integer number;
    private String theme;
    private GregorianCalendar deliveryDate;
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public StudentWork(){}

    public StudentWork(Type type, String discipline, Integer number, String theme, Status status, User owner) {
        this.type = type;
        this.discipline = discipline;
        this.number = number;
        this.theme = theme;
        this.status = status;
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public GregorianCalendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(GregorianCalendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getOwnerName(){
        return owner == null?"<unknown>":owner.getUsername();
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}