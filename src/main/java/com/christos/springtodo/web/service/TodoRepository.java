package com.christos.springtodo.web.service;

import com.christos.springtodo.web.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findByUser(String user);
}
