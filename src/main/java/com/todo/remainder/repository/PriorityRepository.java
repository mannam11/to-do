package com.todo.remainder.repository;

import com.todo.remainder.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriorityRepository extends JpaRepository<Priority,Integer> {
}
