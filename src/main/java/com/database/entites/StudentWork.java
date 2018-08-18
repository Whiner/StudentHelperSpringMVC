package com.database.entites;

import com.database.other.GregorianCalendar;
import com.database.other.Status;
import com.database.other.Type;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class StudentWork {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Type type;
    private String discipline;
    private Integer number;
    private String theme;
    private Date deliveryDate;
    private Status status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    public StudentWork(){}

    public StudentWork(Type type, GregorianCalendar deliveryDate, String discipline, Integer number, String theme, Status status, User owner) {
        this.type = type;
        this.discipline = discipline;
        this.number = number;
        this.theme = theme;
        this.status = status;
        this.owner = owner;
        this.deliveryDate = new Date(deliveryDate.getTime().getTime());
    }

    public Long getId() {
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
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