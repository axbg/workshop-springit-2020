package com.springit.workshop.spring.service.impl;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;
import com.springit.workshop.spring.repository.UserRepository;
import com.springit.workshop.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Pattern emailRegex = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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
    public User register(final String email, final String password) throws SpringBusinessException {
        if (!validateEmail(email)) {
            throw new SpringBusinessException("Email is not valid", HttpStatus.BAD_REQUEST);
        }

        if (!validatePassword(password)) {
            throw new SpringBusinessException("Password is not valid - length should be higher than 5", HttpStatus.BAD_REQUEST);
        }

        User user = new User(email, passwordEncoder.encode(password));

        if (userRepository.findByEmail(email).isPresent()) {
            throw new SpringBusinessException("Email already registered", HttpStatus.BAD_REQUEST);
        }

        return userRepository.save(user);
    }

    @Override
    public User login(final String email, final String password) throws SpringBusinessException {
        Optional<User> user = userRepository.findByEmail(email);

        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.get();
        }

        throw new SpringBusinessException("User not present or credentials not matching", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void reset() {
        User currentUser = getCurrentUser();
        currentUser.setLastReset(Instant.now());
        userRepository.save(currentUser);
    }

    private boolean validateEmail(String email) {
        return emailRegex.matcher(email).matches();
    }

    private boolean validatePassword(String password) {
        return password != null && !password.isBlank() && password.length() > 5;
    }
}