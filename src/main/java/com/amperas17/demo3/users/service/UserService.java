package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.task.TaskEntity;
import com.amperas17.demo3.users.data.user.User;
import com.amperas17.demo3.users.data.user.UserCreds;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserCredsEntity create(UserCredsEntity userEntity);

    List<UserCredsEntity> readAll();

    List<User> getAllExceptCurrent(int id);

    @Nullable
    UserCredsEntity findByCreds(UserCreds userCreds);

    UserCredsEntity update(UserCredsEntity userEntity);

    boolean delete(UUID uuid);

    void addTask(TaskEntity task, int userId);

    List<TaskEntity> getAllTasks();

    @Nullable
    TaskEntity getTaskById(int taskId);

    List<TaskEntity> getAllUsersTasks(int userId);

    List<TaskEntity> getUsersTasksByDate(int userId, long timestamp);

    void editTask(TaskEntity task);

    boolean deleteTask(int id);

    void addSubtask(SubtaskEntity task, int taskId);

    List<SubtaskEntity> readAllSubtasks();

    void editSubtask(SubtaskEntity subtask);

    boolean deleteSubtask(int id);
}
