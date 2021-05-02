package com.amperas17.demo3.users.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id // Сообщяем ORM что это поле - Primary Key
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id = 0;

    @JsonProperty("name")
    @Column(name = "name", length = 25)
    private String name;

    @JsonProperty("email")
    @Column(name = "email", length = 25)
    private String email;

    @JsonProperty("phone")
    @Column(name = "phone", length = 25)
    private String phone;

    public UserEntity(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public UserEntity() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return name.equals(that.name) && email.equals(that.email) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone);
    }
}