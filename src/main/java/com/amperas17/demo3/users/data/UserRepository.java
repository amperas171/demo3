package com.amperas17.demo3.users.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserCredsEntity, Integer> {

    @Query("select u from UserCredsEntity u where u.login = ?1 and u.password = ?2")
    List<UserCredsEntity> findByCreds(String login, String password);
}
