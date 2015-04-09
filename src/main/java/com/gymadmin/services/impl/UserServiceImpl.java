package com.gymadmin.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gymadmin.persistence.dao.UserDao;
import com.gymadmin.persistence.entities.UserEntity;
import com.gymadmin.security.SecurityUtils;
import com.gymadmin.services.UserService;

import javax.inject.Inject;

/**
 * Service class for managing users.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserDao userDao;

    @Transactional(readOnly = true)
    public UserEntity getUserWithAuthorities() {
    	UserEntity currentUser = userDao.findByNick(SecurityUtils.getCurrentLogin());
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

}
