package com.amperas17.demo3.users.data.task;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @JsonProperty("id")
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "tasksIdSeq", sequenceName = "tasks_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasksIdSeq")
    private int id;

    @JsonProperty("name")
    @Column(name = "name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "tasks")
    private Set<UserCredsEntity> users;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<SubtaskEntity> subtasks;

    public TaskEntity() {
    }

    public TaskEntity(String name) {
        this.name = name;
    }

    public TaskEntity(String name, Set<UserCredsEntity> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<UserCredsEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserCredsEntity> users) {
        this.users = users;
    }

    public Set<SubtaskEntity> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<SubtaskEntity> subtasks) {
        this.subtasks = subtasks;
    }

    public void addSubtask(SubtaskEntity subtask) {
        subtasks.add(subtask);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
