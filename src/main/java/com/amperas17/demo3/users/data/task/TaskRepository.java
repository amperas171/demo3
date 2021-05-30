package com.amperas17.demo3.users.data.task;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {

    @Query("select u from TaskEntity u where u.id = ?1")
    TaskEntity findByID(int id);


    @Transactional
    @Modifying
    @Query("delete t from TaskEntity where u.id = ?1")
    void deleteTaskByID(int id);
}
