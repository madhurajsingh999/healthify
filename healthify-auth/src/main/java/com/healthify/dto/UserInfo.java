package com.healthify.dto;

import java.time.LocalDateTime;

public interface UserInfo {
    Integer getId();
    String getUsername();
    String getPassword();
    Boolean getAccountstatusu();
    LocalDateTime getSignupdate();
    LocalDateTime getLastlogin();
    Boolean getEmailverified();
    Boolean getTwofactorenabled();
    Integer getFailedloginattempts();
    Boolean getIscredentialsnonexpired();
    Integer getEmployeedetailid();
    String getFirstname();
    String getLastname();
    String getGender();
    String getEmail();
    String getStatus();
    String getEmployeeid();
    String getEmployeetype();
}
