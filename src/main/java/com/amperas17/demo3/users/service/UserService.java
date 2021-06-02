package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.task.Task;
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

    //void addTaskToUser(TaskEntity task, int userId);

    void addTaskToUserById(int userId, int taskId);

    void removeTaskFromUserById(int userId, int taskId);

    void addNewTaskToUser(Task task, int userId);

    List<TaskEntity> getAllTasks();

    @Nullable
    Task getTaskById(int taskId);

    List<Task> getAllUsersTasks(int userId);

    List<Task> getUsersTasksByDate(int userId, long timestamp);

    void updateTask(Task task);

    boolean deleteTask(int id);

    void addSubtaskToTask(SubtaskEntity task, int taskId);

    List<SubtaskEntity> readAllSubtasks();

    void editSubtask(SubtaskEntity subtask);

    boolean deleteSubtask(int id);
}
