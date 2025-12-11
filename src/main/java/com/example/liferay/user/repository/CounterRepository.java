package com.example.liferay.user.repository;

import com.example.liferay.user.model.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, String> {
}
