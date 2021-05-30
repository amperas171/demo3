package com.amperas17.demo3.users.data.user;

import com.amperas17.demo3.users.data.task.TaskEntity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserCredsEntity {

    //@Id // Сообщяем ORM что это поле - Primary Key
    //@JsonProperty("id")
   // @Column(name = "id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //- для MySql и для постгре на хероку
    //@SequenceGenerator(name = "usersIdSeq", sequenceName = "users_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    //private Integer id = 0;

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    //@SequenceGenerator(name = "usersIdSeq", sequenceName = "users_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersIdSeq")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = 0;

    //@Id
    @JsonProperty("uuid")
    @Column(name = "uuid")
    //@GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "users_tasks", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    private Set<TaskEntity> tasks;

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

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public void addTask(TaskEntity task) {
        tasks.add(task);
    }

    public void deleteTask(TaskEntity task) {
        tasks.remove(task);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCredsEntity that = (UserCredsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(uuid, that.uuid) && Objects.equals(login, that.login) && Objects.equals(password, that.password) && Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(email, that.email) && Objects.equals(phone, that.phone) && Objects.equals(tasks, that.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, login, password, name, surname, email, phone, tasks);
    }
}