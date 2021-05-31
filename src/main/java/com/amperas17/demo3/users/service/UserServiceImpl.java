package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.subtask.SubtaskEntity;
import com.amperas17.demo3.users.data.subtask.SubtaskRepository;
import com.amperas17.demo3.users.data.task.TaskEntity;
import com.amperas17.demo3.users.data.task.TaskRepository;
import com.amperas17.demo3.users.data.user.User;
import com.amperas17.demo3.users.data.user.UserCreds;
import com.amperas17.demo3.users.data.user.UserCredsEntity;
import com.amperas17.demo3.users.data.user.UserRepository;
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

    @Override
    public void addTask(TaskEntity task, int userId) {
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
    public List<TaskEntity> getAllUsersTasks(int userId) {
        UserCredsEntity userCredsEntity = userRepository.findByID(userId);
        return userCredsEntity.getTasks().stream()
                .sorted(Comparator.comparingLong(TaskEntity::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public List<TaskEntity> getUsersTasksByDate(int userId, long timestamp) {
        UserCredsEntity userCredsEntity = userRepository.findByID(userId);
        return userCredsEntity.getTasks().stream()
                .filter(taskEntity -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(timestamp);
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    return taskEntity.getTimestamp() >= timestamp && taskEntity.getTimestamp() < calendar.getTimeInMillis();
                })
                .sorted(Comparator.comparingLong(TaskEntity::getTimestamp))
                .collect(Collectors.toList());
    }

    @Override
    public void editTask(TaskEntity task) {
        taskRepository.save(task);
    }

    //@Transactional
    @Override
    public boolean deleteTask(int id) {
        /*TaskEntity t = taskRepository.findByID(id);
        t.getUsers().clear();
        taskRepository.save(t);*/
        taskRepository.deleteTaskByID(id);
        return taskRepository.findByID(id) == null;
    }

    @Override
    public void addSubtask(SubtaskEntity subtask, int taskId) {
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
        subtaskRepository.updateNameByID(newSubtask.getId(), newSubtask.getName());
    }

    @Override
    public boolean deleteSubtask(int id) {
        subtaskRepository.deleteSubtaskByID(id);
        return subtaskRepository.findByID(id) == null;
    }
}
