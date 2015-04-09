package com.gymadmin.security;

import com.gymadmin.persistence.dao.UserDao;
import com.gymadmin.persistence.entities.UserEntity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String login) {

    	logger.debug("Authenticating {}", login);
        String lowercaseLogin = login.toLowerCase();
        Optional<UserEntity> userFromDatabase =  Optional.ofNullable(userDao.findByNick(lowercaseLogin)); 
        return userFromDatabase.map(user -> {
            if (!user.getEnabled()) {
                throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
            }
            List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                    .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(lowercaseLogin,  user.getPassword(), grantedAuthorities);
        }).orElseThrow(
        		() -> new UsernameNotFoundException("User " + lowercaseLogin + " was not found in the database")
		);
    }
}
