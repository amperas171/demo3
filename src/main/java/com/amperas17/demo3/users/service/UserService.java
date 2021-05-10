package com.amperas17.demo3.users.service;

import com.amperas17.demo3.users.data.UserCreds;
import com.amperas17.demo3.users.data.UserCredsEntity;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.UUID;

public interface UserService {

    boolean create(UserCredsEntity userEntity);

    List<UserCredsEntity> readAll();

    @Nullable
    UserCredsEntity findByCreds(UserCreds userCreds);

    UserCredsEntity update(UserCredsEntity userEntity);

    boolean delete(UUID uuid);
}
