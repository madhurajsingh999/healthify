package com.healthify.config;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.healthify.dto.UserInfo;
import com.healthify.dto.UserRoleInfo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserPrinciple implements UserDetails {
    private static final long serialVersionUID = 1L;

    private long id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @JsonIgnore
    private String password;

    private boolean accountStatus;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(long id, String username, String password,
            Collection<? extends GrantedAuthority> authorities, boolean accountStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountStatus = accountStatus;
    }

    public static UserPrinciple build(UserInfo user,List<UserRoleInfo> roles) {
        List<GrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());

                UserPrinciple userObj= new UserPrinciple(user.getId(), user.getUsername(), user.getPassword(), authorities,user.getAccountstatusu());
                userObj.setEmail(user.getEmail());
                userObj.setFirstName(user.getFirstname());
                userObj.setLastName(user.getLastname());
                return userObj;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.accountStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        UserPrinciple user = (UserPrinciple) o;
        return Objects.equals(id, user.id);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}