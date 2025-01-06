package com.healthify.mapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.healthify.dto.UserInfo;

public class UserRowMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(rs.getInt("id"));
        userInfo.setUsername(rs.getString("username"));
        userInfo.setPassword(rs.getString("password"));
        userInfo.setAccountStatus(rs.getBoolean("accountStatus"));
        userInfo.setSignupDate(rs.getTimestamp("signupDate").toLocalDateTime());
        userInfo.setLastLogin(rs.getTimestamp("lastLogin").toLocalDateTime());
        userInfo.setEmailVerified(rs.getBoolean("emailVerified"));
        userInfo.setTwoFactorEnabled(rs.getBoolean("twoFactorEnabled"));
        userInfo.setFailedLoginAttempts(rs.getInt("failedLoginAttempts"));
        userInfo.setIsCredentialsNonExpired(rs.getBoolean("isCredentialsNonExpired"));
        userInfo.setEmployeeDetailId(rs.getInt("employeeDetailId"));
        userInfo.setFirstName(rs.getString("firstName"));
        userInfo.setLastName(rs.getString("lastName"));
        userInfo.setGender(rs.getString("gender"));
        userInfo.setEmail(rs.getString("email"));
        userInfo.setStatus(rs.getString("status"));
        userInfo.setEmployeeId(rs.getString("employeeId"));
        userInfo.setEmployeeType(rs.getString("employeeType"));
        return userInfo;
    }
}