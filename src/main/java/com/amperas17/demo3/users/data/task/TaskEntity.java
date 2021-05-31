package com.amperas17.demo3.users.data.task;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tasks")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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

    @JsonProperty("status")
    @Column(name = "status")
    private String status;

    @JsonProperty("priority")
    @Column(name = "priority")
    private boolean priority;

    @JsonProperty("note")
    @Column(name = "note")
    private String note;

    @JsonProperty("timestamp")
    @Column(name = "timestamp")
    private long timestamp;

    //@JsonIgnore
    @JsonIgnoreProperties("tasks")
    @ManyToMany(mappedBy = "tasks")
    private Set<UserCredsEntity> users;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnoreProperties("task")
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isPriority() {
        return priority;
    }

    public void setPriority(boolean priority) {
        this.priority = priority;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
