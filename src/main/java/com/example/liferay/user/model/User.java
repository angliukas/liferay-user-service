package com.example.liferay.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "User_")
public class User {

    @Id
    @Column(name = "userId")
    private Long userId;

    @Column(name = "uuid_")
    private String uuid;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "createDate")
    private Timestamp createDate;

    @Column(name = "modifiedDate")
    private Timestamp modifiedDate;

    @Column(name = "defaultUser")
    private Boolean defaultUser;

    @Column(name = "contactId")
    private Long contactId;

    @Column(name = "password_")
    private String password;

    @Column(name = "passwordEncrypted")
    private Boolean passwordEncrypted;

    @Column(name = "passwordReset")
    private Boolean passwordReset;

    @Column(name = "passwordModifiedDate")
    private Timestamp passwordModifiedDate;

    @Column(name = "digest")
    private String digest;

    @Column(name = "reminderQueryQuestion")
    private String reminderQueryQuestion;

    @Column(name = "reminderQueryAnswer")
    private String reminderQueryAnswer;

    @Column(name = "graceLoginCount")
    private Integer graceLoginCount;

    @Column(name = "screenName")
    private String screenName;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "facebookId")
    private Long facebookId;

    @Column(name = "ldapServerId")
    private Long ldapServerId;

    @Column(name = "openId")
    private String openId;

    @Column(name = "portraitId")
    private Long portraitId;

    @Column(name = "languageId")
    private String languageId;

    @Column(name = "timeZoneId")
    private String timeZoneId;

    @Column(name = "greeting")
    private String greeting;

    @Column(name = "comments")
    private String comments;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "status")
    private Integer status;

    @Column(name = "lockout")
    private Boolean lockout;

    @Column(name = "lockoutDate")
    private Timestamp lockoutDate;

    @Column(name = "agreedToTermsOfUse")
    private Boolean agreedToTermsOfUse;

    @Column(name = "emailAddressVerified")
    private Boolean emailAddressVerified;

    @Column(name = "statusDate")
    private Timestamp statusDate;

    @Column(name = "male")
    private Boolean male;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Boolean getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(Boolean defaultUser) {
        this.defaultUser = defaultUser;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(Boolean passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public Boolean getPasswordReset() {
        return passwordReset;
    }

    public void setPasswordReset(Boolean passwordReset) {
        this.passwordReset = passwordReset;
    }

    public Timestamp getPasswordModifiedDate() {
        return passwordModifiedDate;
    }

    public void setPasswordModifiedDate(Timestamp passwordModifiedDate) {
        this.passwordModifiedDate = passwordModifiedDate;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getReminderQueryQuestion() {
        return reminderQueryQuestion;
    }

    public void setReminderQueryQuestion(String reminderQueryQuestion) {
        this.reminderQueryQuestion = reminderQueryQuestion;
    }

    public String getReminderQueryAnswer() {
        return reminderQueryAnswer;
    }

    public void setReminderQueryAnswer(String reminderQueryAnswer) {
        this.reminderQueryAnswer = reminderQueryAnswer;
    }

    public Integer getGraceLoginCount() {
        return graceLoginCount;
    }

    public void setGraceLoginCount(Integer graceLoginCount) {
        this.graceLoginCount = graceLoginCount;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Long getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(Long facebookId) {
        this.facebookId = facebookId;
    }

    public Long getLdapServerId() {
        return ldapServerId;
    }

    public void setLdapServerId(Long ldapServerId) {
        this.ldapServerId = ldapServerId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Long getPortraitId() {
        return portraitId;
    }

    public void setPortraitId(Long portraitId) {
        this.portraitId = portraitId;
    }

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getTimeZoneId() {
        return timeZoneId;
    }

    public void setTimeZoneId(String timeZoneId) {
        this.timeZoneId = timeZoneId;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getLockout() {
        return lockout;
    }

    public void setLockout(Boolean lockout) {
        this.lockout = lockout;
    }

    public Timestamp getLockoutDate() {
        return lockoutDate;
    }

    public void setLockoutDate(Timestamp lockoutDate) {
        this.lockoutDate = lockoutDate;
    }

    public Boolean getAgreedToTermsOfUse() {
        return agreedToTermsOfUse;
    }

    public void setAgreedToTermsOfUse(Boolean agreedToTermsOfUse) {
        this.agreedToTermsOfUse = agreedToTermsOfUse;
    }

    public Boolean getEmailAddressVerified() {
        return emailAddressVerified;
    }

    public void setEmailAddressVerified(Boolean emailAddressVerified) {
        this.emailAddressVerified = emailAddressVerified;
    }

    public Timestamp getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Timestamp statusDate) {
        this.statusDate = statusDate;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }
}
