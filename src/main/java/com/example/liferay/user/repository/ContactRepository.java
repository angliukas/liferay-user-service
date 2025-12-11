package com.example.liferay.user.repository;

import com.example.liferay.user.model.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
}
