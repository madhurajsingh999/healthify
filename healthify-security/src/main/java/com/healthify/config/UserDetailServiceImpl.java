package com.healthify.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.healthify.dao.UserDao;
import com.healthify.dao.UserPrinciple;
import com.healthify.dto.UserInfo;
import com.healthify.dto.UserRoleInfo;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserInfo user = userDao.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        if(user.getStatus().equals("INACTIVE")) {
            throw new UsernameNotFoundException("User is Inactive: " + username);
        }
        List<UserRoleInfo> roles=userDao.findRoleByUserId(user.getId());
        return UserPrinciple.build(user,roles);
    }
}
