package com.amperas17.demo3.users.data.subtask;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface SubtaskRepository extends CrudRepository<SubtaskEntity, Integer> {

    @Query("select s from SubtaskEntity s where u.id = ?1")
    SubtaskEntity findByID(int id);

    @Transactional
    @Modifying
    @Query("update SubtaskEntity s set s.name = ?2 where s.id = ?1")
    void updateNameByID(int id, String name);

    @Transactional
    @Modifying
    @Query("delete from SubtaskEntity s where s.id = ?1")
    void deleteSubtaskByID(int id);
}
