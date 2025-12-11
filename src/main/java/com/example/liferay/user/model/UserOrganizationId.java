package com.example.liferay.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserOrganizationId implements Serializable {

    @Column(name = "organizationId")
    private Long organizationId;

    @Column(name = "userId")
    private Long userId;

    public UserOrganizationId() {
    }

    public UserOrganizationId(Long organizationId, Long userId) {
        this.organizationId = organizationId;
        this.userId = userId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOrganizationId that = (UserOrganizationId) o;
        return Objects.equals(organizationId, that.organizationId)
                && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organizationId, userId);
    }
}
