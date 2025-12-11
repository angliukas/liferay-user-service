package com.example.liferay.user.repository;

import com.example.liferay.user.model.ClassName;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ClassNameRepository extends CrudRepository<ClassName, Long> {
    Optional<ClassName> findByValue(String value);
}
