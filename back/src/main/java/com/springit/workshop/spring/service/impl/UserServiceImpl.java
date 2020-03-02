package com.springit.workshop.spring.service.impl;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;
import com.springit.workshop.spring.repository.UserRepository;
import com.springit.workshop.spring.service.UserService;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User getUserByEmail(final String email) throws SpringBusinessException {
        return userRepository.findByEmail(email).
                orElseThrow(() -> new SpringBusinessException("User not found", HttpStatus.BAD_REQUEST));
    }

    @Override
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public User register(final String email, final String password) {
        return null;
    }

    @Override
    public User login(final String email, final String password) {
        return null;
    }

    @Override
    public void reset() {
        User currentUser = getCurrentUser();
        currentUser.setLastReset(LocalDateTime.now());
        userRepository.save(currentUser);
    }
}