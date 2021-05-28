package com.amperas17.demo3.users.data.subtask;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubtaskRepository extends CrudRepository<SubtaskEntity, Integer> {

    @Query("select u from SubtaskEntity u where u.id = ?1")
    SubtaskEntity findByID(int id);

    @Query("update SubtaskEntity set name = ?2 where id = ?1")
    void updateNameByID(int id, String name);
}
