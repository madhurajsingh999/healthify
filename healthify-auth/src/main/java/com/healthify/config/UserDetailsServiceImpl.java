package com.healthify.config;

import com.healthify.dto.UserInfo;
import com.healthify.dto.UserRoleInfo;
import com.healthify.repository.RoleRepository;
import com.healthify.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserInfo> userOpt = userRepository.findByUsername(username);
        if(userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        UserInfo user = userOpt.get();
        List<UserRoleInfo> roles=roleRepository.findRoleByUserId(user.getId());
        return UserPrinciple.build(user,roles);
    }
}