package com.healthify.repository;

import com.healthify.dto.UserRoleInfo;
import com.healthify.entity.Role;
import com.healthify.enums.RoleName;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName roleName);

    @Query(nativeQuery = true, value = "select ur.user_id userId,ur.role_id roleId,r.name  roleName from user_role ur inner join roles r on r.id=ur.role_id where ur.user_id =:userId")
    List<UserRoleInfo> findRoleByUserId(Integer userId);
}
