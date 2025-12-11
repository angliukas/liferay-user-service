package com.example.liferay.user.service;

import com.example.liferay.user.model.ClassName;
import com.example.liferay.user.model.Contact;
import com.example.liferay.user.model.Counter;
import com.example.liferay.user.model.Role;
import com.example.liferay.user.model.User;
import com.example.liferay.user.model.UserOrganization;
import com.example.liferay.user.model.UserOrganizationId;
import com.example.liferay.user.model.UserRole;
import com.example.liferay.user.model.UserRoleId;
import com.example.liferay.user.repository.ClassNameRepository;
import com.example.liferay.user.repository.ContactRepository;
import com.example.liferay.user.repository.CounterRepository;
import com.example.liferay.user.repository.RoleRepository;
import com.example.liferay.user.repository.UserOrganizationRepository;
import com.example.liferay.user.repository.UserRepository;
import com.example.liferay.user.repository.UserRoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

@Service
public class LiferayUserService {

    private final CounterRepository counterRepository;
    private final UserRepository userRepository;
    private final ContactRepository contactRepository;
    private final ClassNameRepository classNameRepository;
    private final RoleRepository roleRepository;
    private final UserRoleRepository userRoleRepository;
    private final UserOrganizationRepository userOrganizationRepository;

    public LiferayUserService(
            CounterRepository counterRepository,
            UserRepository userRepository,
            ContactRepository contactRepository,
            ClassNameRepository classNameRepository,
            RoleRepository roleRepository,
            UserRoleRepository userRoleRepository,
            UserOrganizationRepository userOrganizationRepository) {
        this.counterRepository = counterRepository;
        this.userRepository = userRepository;
        this.contactRepository = contactRepository;
        this.classNameRepository = classNameRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userOrganizationRepository = userOrganizationRepository;
    }

    @Transactional
    public void createUser(Map<String, String> options) {
        Timestamp now = Timestamp.from(ZonedDateTime.now(ZoneId.of("UTC")).toInstant());
        long userId = nextId(options.getOrDefault("classNameUser", "com.liferay.portal.kernel.model.User"));
        long contactId = nextId(options.getOrDefault("classNameContact", "com.liferay.portal.kernel.model.Contact"));

        User user = createUserEntity(options, now, userId, contactId);
        userRepository.save(user);

        Contact contact = createContact(options, now, userId, contactId);
        contactRepository.save(contact);

        long companyId = Long.parseLong(options.get("companyId"));
        Role crmRole = roleRepository.findByCompanyIdAndName(companyId, "CRM_USER")
                .orElseThrow(() -> new IllegalStateException("roleId not found for CRM_USER in Role_ table"));
        userRoleRepository.save(new UserRole(new UserRoleId(userId, crmRole.getRoleId())));

        userOrganizationRepository.save(new UserOrganization(new UserOrganizationId(1056L, userId)));
    }

    private User createUserEntity(Map<String, String> options, Timestamp now, long userId, long contactId) {
        User user = new User();
        String screenName = options.getOrDefault("screenName", options.get("email"));
        String greeting = "Welcome, " + options.get("firstName") + "!";
        boolean male = Boolean.parseBoolean(options.getOrDefault("male", "true"));

        user.setUserId(userId);
        user.setUuid("");
        user.setCompanyId(Long.parseLong(options.get("companyId")));
        user.setCreateDate(now);
        user.setModifiedDate(now);
        user.setDefaultUser(false);
        user.setContactId(contactId);
        user.setPassword(sha256(options.get("userPassword")));
        user.setPasswordEncrypted(true);
        user.setPasswordReset(false);
        user.setPasswordModifiedDate(now);
        user.setDigest(null);
        user.setReminderQueryQuestion("question");
        user.setReminderQueryAnswer("answer");
        user.setGraceLoginCount(0);
        user.setScreenName(screenName);
        user.setEmailAddress(options.get("email"));
        user.setFacebookId(0L);
        user.setLdapServerId(0L);
        user.setOpenId("");
        user.setPortraitId(0L);
        user.setLanguageId(options.getOrDefault("locale", "en_US"));
        user.setTimeZoneId("UTC");
        user.setGreeting(greeting);
        user.setComments("");
        user.setFirstName(options.get("firstName"));
        user.setMiddleName(options.getOrDefault("middleName", ""));
        user.setLastName(options.get("lastName"));
        user.setJobTitle(options.getOrDefault("jobTitle", ""));
        user.setStatus(0);
        user.setLockout(false);
        user.setLockoutDate(null);
        user.setAgreedToTermsOfUse(true);
        user.setEmailAddressVerified(true);
        user.setStatusDate(now);
        user.setMale(male);
        return user;
    }

    private Contact createContact(Map<String, String> options, Timestamp now, long userId, long contactId) {
        LocalDate birthday = LocalDate.of(
                Integer.parseInt(options.getOrDefault("birthdayYear", "1990")),
                Integer.parseInt(options.getOrDefault("birthdayMonth", "1")),
                Integer.parseInt(options.getOrDefault("birthdayDay", "1")));

        long classNameId = classNameRepository.findByValue(options.getOrDefault(
                        "classNameUser", "com.liferay.portal.kernel.model.User"))
                .map(ClassName::getClassNameId)
                .orElseThrow(() -> new IllegalStateException("classNameId not found in ClassName_ table"));

        Contact contact = new Contact();
        contact.setContactId(contactId);
        contact.setCompanyId(Long.parseLong(options.get("companyId")));
        contact.setUserId(userId);
        contact.setUserName(options.get("firstName"));
        contact.setCreateDate(now);
        contact.setModifiedDate(now);
        contact.setClassNameId(classNameId);
        contact.setClassPK(userId);
        contact.setAccountId(0L);
        contact.setParentContactId(0L);
        contact.setEmailAddress(options.get("email"));
        contact.setFirstName(options.get("firstName"));
        contact.setMiddleName(options.getOrDefault("middleName", ""));
        contact.setLastName(options.get("lastName"));
        contact.setPrefixId(0);
        contact.setSuffixId(0);
        contact.setMale(Boolean.parseBoolean(options.getOrDefault("male", "true")));
        contact.setBirthday(Date.valueOf(birthday));
        contact.setSmsSn("");
        contact.setAimSn("");
        contact.setFacebookSn("");
        contact.setIcqSn("");
        contact.setJabberSn("");
        contact.setMsnSn("");
        contact.setMySpaceSn("");
        contact.setSkypeSn("");
        contact.setTwitterSn("");
        contact.setYmSn("");
        contact.setEmployeeStatusId("");
        contact.setEmployeeNumber("");
        contact.setJobTitle(options.getOrDefault("jobTitle", ""));
        contact.setJobClass("");
        contact.setHoursOfOperation("");
        return contact;
    }

    private long nextId(String counterName) {
        Counter counter = counterRepository.findById(counterName).orElse(null);
        if (counter == null) {
            counter = new Counter();
            counter.setName(counterName);
            counter.setCurrentId(10000L);
            counterRepository.save(counter);
            return 10000L;
        }

        Long currentId = counter.getCurrentId();
        long nextId = currentId == null ? 10000L : currentId + 1;
        counter.setCurrentId(nextId);
        counterRepository.save(counter);
        return nextId;
    }

    private String sha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashed = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}
