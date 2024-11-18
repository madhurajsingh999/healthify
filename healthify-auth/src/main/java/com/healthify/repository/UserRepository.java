package com.healthify.repository;

import com.healthify.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    @Query("FROM User u WHERE u.username=:username OR u.email=:username")
    Optional<User> findByUsernameOrEmail(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}