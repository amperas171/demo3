package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.subtask.Subtask;
import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.subtask.SubtaskRepository;
import com.amperas17.demo3.users.data.task.Task;
import com.amperas17.demo3.users.data.task.TaskEntity;
import com.amperas17.demo3.users.data.task.TaskRepository;
import com.amperas17.demo3.users.data.user.User;
import com.amperas17.demo3.users.data.user.UserCreds;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import com.amperas17.demo3.users.data.user.UserRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private TaskRepository taskRepository;
    private SubtaskRepository subtaskRepository;

    public UserServiceImpl(UserRepository userRepository, TaskRepository taskRepository, SubtaskRepository subtaskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.subtaskRepository = subtaskRepository;
    }

    @Override
    public UserCredsEntity create(UserCredsEntity creds) {
        List<UserCredsEntity> users = userRepository.findByLogin(creds.getLogin());
        if (users.isEmpty()) {
            creds.setUuid(UUID.randomUUID());
            UserCredsEntity u = userRepository.save(creds);
            return u;
        } else {
            return null;
        }
    }

    @Override
    public List<UserCredsEntity> readAll() {
        List<UserCredsEntity> userEntities = new ArrayList<>();
        for (UserCredsEntity userEntity : userRepository.findAll()) {
            userEntities.add(userEntity);
        }
        return userEntities;
    }

    @Override
    public List<User> getAllExceptCurrent(int id) {
        List<User> users = new ArrayList<>();
        for (UserCredsEntity uce : userRepository.getAllExceptCurrent(id)) {
            users.add(new User(uce.getId(), uce.getName(), uce.getSurname(), uce.getEmail(), uce.getPhone()));
        }
        return users;
    }

    @Override
    public UserCredsEntity findByCreds(UserCreds userCreds) {
        List<UserCredsEntity> userCredsEntities = userRepository.findByCreds(userCreds.getLogin(), userCreds.getPassword());
        return userCredsEntities.isEmpty()
                ? null
                : userCredsEntities.get(0);
    }

    @Override
    public UserCredsEntity update(UserCredsEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public boolean delete(UUID uuid) {
        userRepository.delete(uuid);
        return userRepository.findByUUID(uuid).isEmpty();
    }

    /*@Override
    public void addTaskToUser(TaskEntity task, int userId) {
        TaskEntity taskEntity = taskRepository.save(task);
        UserCredsEntity uce = userRepository.findByID(userId);
        HashSet<UserCredsEntity> usersSet = new HashSet<>();
        usersSet.add(uce);
        taskEntity.setUsers(usersSet);
        if (uce.getTasks() == null) {
            uce.setTasks(new HashSet<>());
        }
        uce.addTask(taskEntity);
        taskEntity.getUsers().add(uce);
        taskRepository.save(taskEntity);
        userRepository.save(uce);
    }*/

    @Override
    public void addNewTaskToUser(Task task, int userId) {
        TaskEntity taskEntity = taskRepository.save(new TaskEntity(task.getName(), task.getStatus(), task.isPriority(), task.getNote(), task.getTimestamp()));
        UserCredsEntity uce = userRepository.findByID(userId);
        HashSet<UserCredsEntity> usersSet = new HashSet<>();
        usersSet.add(uce);
        taskEntity.setUsers(usersSet);
        if (uce.getTasks() == null) {
            uce.setTasks(new HashSet<>());
        }
        uce.addTask(taskEntity);
        taskEntity.getUsers().add(uce);
        taskRepository.save(taskEntity);
        userRepository.save(uce);
    }

    @Override
    public void addTaskToUserById(int userId, int taskId) {
        TaskEntity taskEntity = taskRepository.findByID(taskId);
        UserCredsEntity uce = userRepository.findByID(userId);
        HashSet<UserCredsEntity> usersSet = new HashSet<>();
        usersSet.add(uce);
        taskEntity.setUsers(usersSet);
        if (uce.getTasks() == null) {
            uce.setTasks(new HashSet<>());
        }
        uce.addTask(taskEntity);
        taskEntity.getUsers().add(uce);
        taskRepository.save(taskEntity);
        userRepository.save(uce);
    }

    @Override
    public void removeTaskFromUserById(int userId, int taskId) {
        TaskEntity taskEntity = taskRepository.findByID(taskId);
        UserCredsEntity uce = userRepository.findByID(userId);
        if (taskEntity.getUsers() != null) {
            taskEntity.getUsers().remove(uce);
        }
        if (uce.getTasks() != null) {
            uce.getTasks().remove(taskEntity);
        }
        taskRepository.save(taskEntity);
        userRepository.save(uce);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        List<TaskEntity> taskEntities = new ArrayList<>();
        for (TaskEntity taskEntity : taskRepository.findAll()) {
            taskEntities.add(taskEntity);
        }
        return taskEntities;
    }

    @Override
    public Task getTaskById(int taskId) {
        return getTask(taskRepository.findByID(taskId));
    }

    @Override
    public List<Task> getAllUsersTasks(int userId) {
        UserCredsEntity userCredsEntity = userRepository.findByID(userId);
        List<TaskEntity> sorted = userCredsEntity.getTasks().stream()
                .sorted(Comparator.comparingLong(TaskEntity::getTimestamp))
                .collect(Collectors.toList());
        List<Task> tasks = new ArrayList<>();
        for (TaskEntity te: sorted) {
            tasks.add(getTask(te));
        }
        return tasks;
    }

    @Override
    public List<Task> getUsersTasksByDate(int userId, long timestamp) {
        UserCredsEntity userCredsEntity = userRepository.findByID(userId);
        List<TaskEntity> sorted = userCredsEntity.getTasks().stream()
                .filter(taskEntity -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timestamp);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    return taskEntity.getTimestamp() >= timestamp && taskEntity.getTimestamp() < calendar.getTimeInMillis();
                })
                .sorted(Comparator.comparingLong(TaskEntity::getTimestamp))
                .collect(Collectors.toList());
        List<Task> tasks = new ArrayList<>();
        for (TaskEntity te: sorted) {
            tasks.add(getTask(te));
        }
        return tasks;
    }

    @Nullable
    private Task getTask(@Nullable TaskEntity te) {
        if (te == null) {
            return null;
        }
        Set<User> users = new HashSet<>();
        if (te.getUsers() != null) {
            for (UserCredsEntity uce : te.getUsers()) {
                users.add(new User(uce.getId(), uce.getName(), uce.getSurname(), uce.getEmail(), uce.getPhone()));
            }
        }
        Set<Subtask> subtasks = new HashSet<>();
        if (te.getSubtasks() != null) {
            for (SubtaskEntity s : te.getSubtasks()) {
                subtasks.add(new Subtask(s.getId(), s.getName(), s.getStatus()));
            }
        }
        return new Task(te.getId(), te.getName(), te.getStatus(), te.isPriority(), te.getNote(), te.getTimestamp(), users, subtasks);
    }

    @Override
    public void editTask(Task task) {
        taskRepository.updateTaskByID(task.getId(), task.getName(), task.getStatus(), task.isPriority(), task.getNote(), task.getTimestamp());
        TaskEntity newTaskEntity = taskRepository.findByID(task.getId());
        if (newTaskEntity.getSubtasks() != null) {
            for (SubtaskEntity se : newTaskEntity.getSubtasks()) {
                subtaskRepository.deleteSubtaskByID(se.getId());
            }
        }
        if (task.getSubtasks() != null) {
            for (Subtask subtask : task.getSubtasks()) {
                SubtaskEntity subtaskEntity = new SubtaskEntity(subtask.getName(), subtask.getStatus());;
                if (subtask.getId() > 0) {
                    subtaskEntity.setId(subtask.getId());
                }
                addSubtaskToTask(subtaskEntity, newTaskEntity.getId());
            }
        }
    }

    @Override
    public boolean deleteTask(int id) {
        TaskEntity te = taskRepository.findByID(id);
        for (SubtaskEntity se : te.getSubtasks()) {
            subtaskRepository.deleteSubtaskByID(se.getId());
        }
        taskRepository.deleteTaskByID(id);
        return taskRepository.findByID(id) == null;
    }

    @Override
    public void addSubtaskToTask(SubtaskEntity subtask, int taskId) {
        SubtaskEntity subtaskEntity = subtaskRepository.save(subtask);
        TaskEntity taskEntity = taskRepository.findByID(taskId);

        if (taskEntity.getSubtasks() == null) {
            taskEntity.setSubtasks(new HashSet<>());
        }
        taskEntity.addSubtask(subtaskEntity);
        subtaskEntity.setTaskEntity(taskEntity);

        subtaskRepository.save(subtaskEntity);
        taskRepository.save(taskEntity);
    }

    @Override
    public List<SubtaskEntity> readAllSubtasks() {
        List<SubtaskEntity> subtaskEntities = new ArrayList<>();
        for (SubtaskEntity subtaskEntity : subtaskRepository.findAll()) {
            subtaskEntities.add(subtaskEntity);
        }
        return subtaskEntities;
    }

    @Override
    public void editSubtask(SubtaskEntity newSubtask) {
        subtaskRepository.updateSubtaskByID(newSubtask.getId(), newSubtask.getName(), newSubtask.getStatus());
    }

    @Override
    public boolean deleteSubtask(int id) {
        subtaskRepository.deleteSubtaskByID(id);
        return subtaskRepository.findByID(id) == null;
    }
}
