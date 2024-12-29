package com.healthify.repository;

import com.healthify.dto.UserInfo;
import com.healthify.entity.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(nativeQuery = true, value = "select u.id,u.username, u.password,u.account_status accountStatusu,u.signup_date signupDate,u.last_login lastLogin,u.email_verified emailVerified,u.two_factor_enabled twoFactorEnabled,u.failed_login_attempts failedLoginAttempts,u.is_credentials_non_expired isCredentialsNonExpired,nullif(e.id,0) employeeDetailId,e.first_name firstName,e.last_name  lastName,e.gender ,e.email ,e.status ,e.employee_id employeeId,e.employee_type employeeType from users u left outer join employee_details e on u.employee_detail_id=e.id where u.username =:username")
    Optional<UserInfo> findByUsername(String username);

    Boolean existsByUsername(String username);
}