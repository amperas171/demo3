package com.amperas17.demo3.users.data.subtask;

import com.amperas17.demo3.users.data.task.TaskEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "subtasks")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "taskEntity"})
public class SubtaskEntity {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    private TaskEntity task;

    public SubtaskEntity() {
    }

    public SubtaskEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskEntity getTaskEntity() {
        return task;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.task = taskEntity;
    }
}
