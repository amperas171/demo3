package com.amperas17.demo3.users.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserCredsEntity, UUID> {

    @Query("select u from UserCredsEntity u where u.login = ?1 and u.password = ?2")
    List<UserCredsEntity> findByCreds(String login, String password);

    @Query("select u from UserCredsEntity u where u.login = ?1")
    List<UserCredsEntity> findByLogin(String login);
}
