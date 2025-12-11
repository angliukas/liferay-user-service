package com.example.liferay.user.repository;

import com.example.liferay.user.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
