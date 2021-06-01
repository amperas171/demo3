package com.amperas17.demo3.users.data.task;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {

    @Query("select t from TaskEntity t where t.id = ?1")
    TaskEntity findByID(int id);

    @Transactional
    @Modifying
    @Query("update TaskEntity t set t.name = ?2, t.status = ?3, t.priority = ?4, t.note = ?5, t.timestamp = ?6 where t.id = ?1")
    void updateTaskByID(int id, String name, String status, boolean priority, String note, long timestamp);

    @Transactional
    @Modifying
    @Query("delete from TaskEntity t where t.id = ?1")
    void deleteTaskByID(int id);
}
