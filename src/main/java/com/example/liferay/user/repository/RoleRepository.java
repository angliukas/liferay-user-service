package com.example.liferay.user.repository;

import com.example.liferay.user.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByCompanyIdAndName(Long companyId, String name);
}
