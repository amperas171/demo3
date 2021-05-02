package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.UserEntity;
import com.amperas17.demo3.users.data.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(UserEntity userEntity) {
        userRepository.save(userEntity);
    }

    @Override
    public List<UserEntity> readAll() {
        List<UserEntity> userEntities = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            userEntities.add(userEntity);
        }
        return userEntities;
    }

    @Override
    public UserEntity read(int id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public UserEntity update(UserEntity userEntity, int id) {
        return userRepository.save(userEntity);
    }

    @Override
    public boolean delete(int id) {
        userRepository.deleteById(id);
        return !userRepository.existsById(id);
    }
}
