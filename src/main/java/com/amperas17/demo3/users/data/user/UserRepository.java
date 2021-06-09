package com.amperas17.demo3.users.data.user;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserCredsEntity, Integer> {

    @Query("select u from UserCredsEntity u where u.login = ?1 and u.password = ?2")
    List<UserCredsEntity> findByCreds(String login, String password);

    @Query("select u from UserCredsEntity u where u.login = ?1")
    List<UserCredsEntity> findByLogin(String login);

    @Query("select u from UserCredsEntity u where u.id != ?1")
    List<UserCredsEntity> getAllExceptCurrent(int id);

    //@Query("select u from UserCredsEntity u where u.id != ?1 AND position(?2 in u.name)>0")
    //List<UserCredsEntity> getAllExceptCurrentByQuery(int id, String query);

    @Query("select u from UserCredsEntity u where u.id != ?1 AND to_tsvecor(u.name) @@ plainto_tsquery(query)")
    List<UserCredsEntity> getAllExceptCurrentByQuery(int id, String query);

    @Transactional
    @Modifying
    @Query("delete from UserCredsEntity where uuid = ?1")
    void delete(UUID uuid);

    @Query("select u from UserCredsEntity u where u.uuid = ?1")
    List<UserCredsEntity> findByUUID(UUID uuid);

    @Query("select u from UserCredsEntity u where u.id = ?1")
    UserCredsEntity findByID(int id);
}
