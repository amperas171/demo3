package com.amperas17.demo3.users.data.subtask;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SubtaskRepository extends CrudRepository<SubtaskEntity, Integer> {

    @Query("select u from SubtaskEntity u where u.id = ?1")
    SubtaskEntity findByID(long id);
}
