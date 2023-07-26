package com.platform.repository;

import  com.platform.user.UserDAO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDAO, Integer> {
    UserDAO findByEmail(String email);
}
