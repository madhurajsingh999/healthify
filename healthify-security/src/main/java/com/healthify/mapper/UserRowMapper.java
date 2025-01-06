package com.healthify.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import com.healthify.dto.UserInfo;

public class UserRowMapper implements RowMapper<UserInfo> {
    @Override
    public UserInfo mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        UserInfo employee = new UserInfo();
        //employee.setId(rs.getLong("id"));
        //employee.setName(rs.getString("name"));
        //employee.setDepartment(rs.getString("department"));
        return employee;
    }
}
