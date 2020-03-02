package com.springit.workshop.spring.service;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;

public interface UserService {
    User getUserByEmail(String email) throws SpringBusinessException;

    User getCurrentUser();

    User register(String email, String password) throws SpringBusinessException;

    User login(String email, String password) throws SpringBusinessException;

    void reset();
}