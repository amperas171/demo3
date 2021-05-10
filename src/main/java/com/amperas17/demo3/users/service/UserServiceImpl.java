package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.UserCreds;
import com.amperas17.demo3.users.data.UserCredsEntity;
import com.amperas17.demo3.users.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean create(UserCredsEntity creds) {
        List<UserCredsEntity> users = userRepository.findByLogin(creds.getLogin());
        if (users.isEmpty()) {
            userRepository.save(creds);
            return true;
        } else {
            return false;
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
        userRepository.deleteById(uuid);
        return !userRepository.existsById(uuid);
    }
}
