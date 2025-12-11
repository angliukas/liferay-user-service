package com.example.liferay.user.repository;

import com.example.liferay.user.model.UserRole;
import com.example.liferay.user.model.UserRoleId;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, UserRoleId> {
}
