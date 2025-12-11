package com.example.liferay.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "Contact_")
public class Contact {

    @Id
    @Column(name = "contactId")
    private Long contactId;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "createDate")
    private Timestamp createDate;

    @Column(name = "modifiedDate")
    private Timestamp modifiedDate;

    @Column(name = "classNameId")
    private Long classNameId;

    @Column(name = "classPK")
    private Long classPK;

    @Column(name = "accountId")
    private Long accountId;

    @Column(name = "parentContactId")
    private Long parentContactId;

    @Column(name = "emailAddress")
    private String emailAddress;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "middleName")
    private String middleName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "prefixId")
    private Integer prefixId;

    @Column(name = "suffixId")
    private Integer suffixId;

    @Column(name = "male")
    private Boolean male;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "smsSn")
    private String smsSn;

    @Column(name = "aimSn")
    private String aimSn;

    @Column(name = "facebookSn")
    private String facebookSn;

    @Column(name = "icqSn")
    private String icqSn;

    @Column(name = "jabberSn")
    private String jabberSn;

    @Column(name = "msnSn")
    private String msnSn;

    @Column(name = "mySpaceSn")
    private String mySpaceSn;

    @Column(name = "skypeSn")
    private String skypeSn;

    @Column(name = "twitterSn")
    private String twitterSn;

    @Column(name = "ymSn")
    private String ymSn;

    @Column(name = "employeeStatusId")
    private String employeeStatusId;

    @Column(name = "employeeNumber")
    private String employeeNumber;

    @Column(name = "jobTitle")
    private String jobTitle;

    @Column(name = "jobClass")
    private String jobClass;

    @Column(name = "hoursOfOperation")
    private String hoursOfOperation;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public Long getClassNameId() {
        return classNameId;
    }

    public void setClassNameId(Long classNameId) {
        this.classNameId = classNameId;
    }

    public Long getClassPK() {
        return classPK;
    }

    public void setClassPK(Long classPK) {
        this.classPK = classPK;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getParentContactId() {
        return parentContactId;
    }

    public void setParentContactId(Long parentContactId) {
        this.parentContactId = parentContactId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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

    public Integer getPrefixId() {
        return prefixId;
    }

    public void setPrefixId(Integer prefixId) {
        this.prefixId = prefixId;
    }

    public Integer getSuffixId() {
        return suffixId;
    }

    public void setSuffixId(Integer suffixId) {
        this.suffixId = suffixId;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSmsSn() {
        return smsSn;
    }

    public void setSmsSn(String smsSn) {
        this.smsSn = smsSn;
    }

    public String getAimSn() {
        return aimSn;
    }

    public void setAimSn(String aimSn) {
        this.aimSn = aimSn;
    }

    public String getFacebookSn() {
        return facebookSn;
    }

    public void setFacebookSn(String facebookSn) {
        this.facebookSn = facebookSn;
    }

    public String getIcqSn() {
        return icqSn;
    }

    public void setIcqSn(String icqSn) {
        this.icqSn = icqSn;
    }

    public String getJabberSn() {
        return jabberSn;
    }

    public void setJabberSn(String jabberSn) {
        this.jabberSn = jabberSn;
    }

    public String getMsnSn() {
        return msnSn;
    }

    public void setMsnSn(String msnSn) {
        this.msnSn = msnSn;
    }

    public String getMySpaceSn() {
        return mySpaceSn;
    }

    public void setMySpaceSn(String mySpaceSn) {
        this.mySpaceSn = mySpaceSn;
    }

    public String getSkypeSn() {
        return skypeSn;
    }

    public void setSkypeSn(String skypeSn) {
        this.skypeSn = skypeSn;
    }

    public String getTwitterSn() {
        return twitterSn;
    }

    public void setTwitterSn(String twitterSn) {
        this.twitterSn = twitterSn;
    }

    public String getYmSn() {
        return ymSn;
    }

    public void setYmSn(String ymSn) {
        this.ymSn = ymSn;
    }

    public String getEmployeeStatusId() {
        return employeeStatusId;
    }

    public void setEmployeeStatusId(String employeeStatusId) {
        this.employeeStatusId = employeeStatusId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }
}
