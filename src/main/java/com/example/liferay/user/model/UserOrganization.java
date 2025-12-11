package com.example.liferay.user.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users_Orgs")
public class UserOrganization {

    @EmbeddedId
    private UserOrganizationId id;

    public UserOrganization() {
    }

    public UserOrganization(UserOrganizationId id) {
        this.id = id;
    }

    public UserOrganizationId getId() {
        return id;
    }

    public void setId(UserOrganizationId id) {
        this.id = id;
    }
}
