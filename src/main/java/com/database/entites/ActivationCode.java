package com.database.entites;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "activation_codes")
public class ActivationCode {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(unique = true)
    private String code;

    public ActivationCode() {
        generate();
    }

    public ActivationCode(User user, String code) {
        this.user = user;
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void generate() {
        code = UUID.randomUUID().toString();
    }
}
