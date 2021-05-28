package com.amperas17.demo3.users.data.user;

import java.util.UUID;

public class CurrentUser {

    private UUID uuid;
    private int id;
    private String name;
    private String surname;
    private String email;
    private String phone;

    public CurrentUser() {
    }

    public CurrentUser(UUID uuid, int id, String name, String surname, String email, String phone) {
        this.uuid = uuid;
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
