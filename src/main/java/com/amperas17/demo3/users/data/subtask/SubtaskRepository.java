package com.amperas17.demo3.users.data.subtask;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SubtaskRepository extends CrudRepository<SubtaskEntity, Integer> {

    @Query("select u from SubtaskEntity u where u.id = ?1")
    SubtaskEntity findByID(int id);

    @Transactional
    @Modifying
    @Query("update SubtaskEntity u set u.name = ?2 where u.id = ?1")
    void updateNameByID(int id, String name);
}
