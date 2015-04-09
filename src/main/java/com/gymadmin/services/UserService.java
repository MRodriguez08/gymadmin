package com.gymadmin.services;

import com.gymadmin.persistence.entities.UserEntity;

/**
 * Service class for managing users.
 */
public interface UserService {

    public UserEntity getUserWithAuthorities();

}
