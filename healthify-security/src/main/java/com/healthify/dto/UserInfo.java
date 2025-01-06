package com.healthify.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private Boolean accountStatus;
    private LocalDateTime signupDate;
    private LocalDateTime lastLogin;
    private Boolean emailVerified;
    private Boolean twoFactorEnabled;
    private Integer failedLoginAttempts;
    private Boolean isCredentialsNonExpired;
    private Integer employeeDetailId;
    private String firstName;
    private String lastName;
    private String gender;
    private String email;
    private String status;
    private String employeeId;
    private String employeeType;
}
