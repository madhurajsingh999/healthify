package com.healthify.dao;

import com.healthify.dto.UserRoleInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRoleInfoRowMapper implements RowMapper<UserRoleInfo> {
    @Override
    public UserRoleInfo mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        UserRoleInfo userRoleInfo = new UserRoleInfo();
        userRoleInfo.setUserId(rs.getInt("userId"));
        userRoleInfo.setRoleId(rs.getInt("roleId"));
        userRoleInfo.setRoleName(rs.getString("roleName"));
        return userRoleInfo;
    }
}
