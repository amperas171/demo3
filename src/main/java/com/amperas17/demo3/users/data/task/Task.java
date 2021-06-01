package com.amperas17.demo3.users.data.task;

import com.amperas17.demo3.users.data.subtask.Subtask;
import com.amperas17.demo3.users.data.user.User;

import java.util.Set;

public class Task {

    private int id;
    private String name;
    private String status;
    private boolean priority;
    private String note;
    private long timestamp;
    private Set<User> users;
    private Set<Subtask> subtasks;

    public Task(int id, String name, String status, boolean priority, String note, long timestamp, Set<User> users, Set<Subtask> subtasks) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.priority = priority;
        this.note = note;
        this.timestamp = timestamp;
        this.users = users;
        this.subtasks = subtasks;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Subtask> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(Set<Subtask> subtasks) {
        this.subtasks = subtasks;
    }
}
