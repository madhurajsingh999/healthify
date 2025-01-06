package com.healthify.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import com.healthify.dto.UserInfo;
import com.healthify.dto.UserRoleInfo;
import com.healthify.mapper.UserRoleInfoRowMapper;
import com.healthify.mapper.UserRowMapper;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserInfo findByUsername(String username) {
        String sql = "select u.id, u.username, u.password, u.account_status accountStatus, u.signup_date signupDate, u.last_login lastLogin, u.email_verified emailVerified, u.two_factor_enabled twoFactorEnabled, u.failed_login_attempts failedLoginAttempts, u.is_credentials_non_expired isCredentialsNonExpired, nullif(e.id, 0) employeeDetailId, e.first_name firstName, e.last_name lastName, e.gender, e.email, e.status, e.employee_id employeeId, e.employee_type employeeType from users u left outer join employee_details e on u.employee_detail_id = e.id where u.username = ?";

        List<UserInfo> users = jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps) throws SQLException {
                ps.setString(1, username);
            }
        }, new UserRowMapper());

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    public List<UserRoleInfo> findRoleByUserId(Integer userId) {
        String sql = "select ur.user_id userId, ur.role_id roleId, r.name roleName from user_role ur inner join roles r on r.id = ur.role_id where ur.user_id = ?";
        return jdbcTemplate.query(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(@NonNull PreparedStatement ps) throws SQLException {
                ps.setInt(1, userId);
            }
        }, new UserRoleInfoRowMapper());
    }
}
