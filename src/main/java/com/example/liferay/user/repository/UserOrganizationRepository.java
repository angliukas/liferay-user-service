package com.example.liferay.user.repository;

import com.example.liferay.user.model.UserOrganization;
import com.example.liferay.user.model.UserOrganizationId;
import org.springframework.data.repository.CrudRepository;

public interface UserOrganizationRepository extends CrudRepository<UserOrganization, UserOrganizationId> {
}
