package com.springit.workshop.spring.controller;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;
import com.springit.workshop.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.rest.prefix}/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws SpringBusinessException {
        isUserValid(user);
        User foundUser = userService.register(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws SpringBusinessException {
        isUserValid(user);
        User foundUser = userService.login(user.getEmail(), user.getPassword());
        return ResponseEntity.ok(foundUser);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        userService.reset();
        return ResponseEntity.ok().build();
    }

    private void isUserValid(User user) throws SpringBusinessException {
        if (user == null || user.getEmail().isBlank() || user.getPassword().isBlank()) {
            throw new SpringBusinessException("Request should contain email and password", HttpStatus.BAD_REQUEST);
        }
    }
}
