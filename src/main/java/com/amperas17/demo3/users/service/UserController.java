package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.task.Task;
import com.amperas17.demo3.users.data.task.TaskEntity;
import com.amperas17.demo3.users.data.user.CurrentUser;
import com.amperas17.demo3.users.data.user.User;
import com.amperas17.demo3.users.data.user.UserCreds;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/users/create")
    public ResponseEntity<CurrentUser> create(@RequestBody UserCredsEntity user) {
        UserCredsEntity uce = userService.create(user);
        return uce != null
                ? new ResponseEntity<>(new CurrentUser(uce.getUuid(), uce.getId(), uce.getName(), uce.getSurname(), uce.getEmail(), uce.getPhone()), HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // TODO delete before release
    @GetMapping(value = "/users/getAll")
    public ResponseEntity<List<UserCredsEntity>> read() {
        final List<UserCredsEntity> users = userService.readAll();

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/getAllOther/{id}")
    public ResponseEntity<List<User>> getAll(@PathVariable(name = "id") int id) {
        final List<User> users = userService.getAllExceptCurrent(id);

        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users/login")
    public ResponseEntity<CurrentUser> login(@RequestBody UserCreds userCreds) {
        final UserCredsEntity uce = userService.findByCreds(userCreds);

        return uce != null
                ? new ResponseEntity<>(new CurrentUser(uce.getUuid(), uce.getId(), uce.getName(), uce.getSurname(), uce.getEmail(), uce.getPhone()), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/users/update")
    public ResponseEntity<?> update(@RequestBody UserCredsEntity user) {
        final UserCredsEntity userUpdated = userService.update(user);

        return userUpdated != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/users/delete/{uuid}")
    public ResponseEntity<?> delete(@PathVariable(name = "uuid") UUID uuid) {
        final boolean deleted = userService.delete(uuid);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    /*@PostMapping(value = "/users/{userId}/addTask")
    public ResponseEntity<?> addTaskToUser(@RequestBody TaskEntity taskEntity, @PathVariable(name = "userId") int userId) {
        userService.addTaskToUser(taskEntity, userId);

        return taskEntity != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/

    @GetMapping(value = "/users/{userId}/addTask/{taskId}")
    public ResponseEntity<?> addTaskToUserById(@PathVariable(name = "userId") int userId, @PathVariable(name = "taskId") int taskId) {
        userService.addTaskToUserById(userId, taskId);

        return taskId > 0
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{userId}/removeTask/{taskId}")
    public ResponseEntity<?> removeTaskFromUserById(@PathVariable(name = "userId") int userId, @PathVariable(name = "taskId") int taskId) {
        userService.removeTaskFromUserById(userId, taskId);

        return taskId > 0
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users/{userId}/addNewTask")
    public ResponseEntity<?> addNewTaskToUser(@RequestBody Task task, @PathVariable(name = "userId") int userId) {
        userService.addNewTaskToUser(task, userId);

        return task != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/tasks/update")
    public ResponseEntity<?> updateTask(@RequestBody Task task) {
        userService.updateTask(task);

        return task != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/tasks/{id}/delete")
    public ResponseEntity<?> deleteTask(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.deleteTask(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // TODO delete before release
    @GetMapping(value = "/tasks/getAll")
    public ResponseEntity<List<TaskEntity>> getTasks() {
        final List<TaskEntity> tasks = userService.getAllTasks();

        return tasks != null && !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/tasks/{id}/get")
    public ResponseEntity<Task> getTask(@PathVariable(name = "id") int id) {
        final Task task = userService.getTaskById(id);

        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{userId}/getTasks")
    public List<Task> getAllUsersTasks(@PathVariable(name = "userId") int userId) {
        return userService.getAllUsersTasks(userId);
    }

    @GetMapping(value = "/users/{userId}/getTasks/{timestamp}")
    public List<Task> getUsersTasksByDate(@PathVariable(name = "userId") int userId, @PathVariable(name = "timestamp") long timestamp) {
        return userService.getUsersTasksByDate(userId, timestamp);
    }

    @PostMapping(value = "/tasks/{taskId}/addSubtask")
    public ResponseEntity<?> addSubtaskToTask(@RequestBody SubtaskEntity subtaskEntity, @PathVariable(name = "taskId") int taskId) {
        userService.addSubtaskToTask(subtaskEntity, taskId);

        return subtaskEntity != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // TODO delete before release
    @GetMapping(value = "/subtasks/getAll")
    public ResponseEntity<List<SubtaskEntity>> getSubtasks() {
        final List<SubtaskEntity> subtasks = userService.readAllSubtasks();

        return subtasks != null && !subtasks.isEmpty()
                ? new ResponseEntity<>(subtasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/subtasks/update")
    public ResponseEntity<?> updateSubtask(@RequestBody SubtaskEntity subtaskEntity) {
        userService.editSubtask(subtaskEntity);

        return subtaskEntity != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/subtasks/{id}/delete")
    public ResponseEntity<?> deleteSubtask(@PathVariable(name = "id") int id) {
        final boolean deleted = userService.deleteSubtask(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}