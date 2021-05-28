package com.amperas17.demo3.users.data.task;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<TaskEntity, Integer> {

    @Query("select u from TaskEntity u where u.id = ?1")
    TaskEntity findByID(long id);
}
