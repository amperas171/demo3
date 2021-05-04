package com.amperas17.demo3.users.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserCredsEntity {

    @Id // Сообщяем ORM что это поле - Primary Key
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO) //- для MySql и для постгре на хероку
    //@SequenceGenerator(name = "usersIdSeq", sequenceName = "users_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    private Integer id = 0;

    @JsonProperty("login")
    @Column(name = "login", length = 25)
    private String login;

    @JsonProperty("password")
    @Column(name = "password", length = 25)
    private String password;

    @JsonProperty("name")
    @Column(name = "name", length = 25)
    private String name;

    @JsonProperty("surname")
    @Column(name = "surname", length = 25)
    private String surname;

    @JsonProperty("email")
    @Column(name = "email", length = 25)
    private String email;

    @JsonProperty("phone")
    @Column(name = "phone", length = 25)
    private String phone;

    public UserCredsEntity() {}

    public UserCredsEntity(String login, String password, String name, String surname, String email, String phone) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}