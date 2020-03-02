package com.springit.workshop.spring.controller;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.User;
import com.springit.workshop.spring.security.JwtUtils;
import com.springit.workshop.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.rest.prefix}/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) throws SpringBusinessException {
        isUserValid(user);
        userService.register(user.getEmail(), user.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user, HttpServletResponse response) throws SpringBusinessException {
        isUserValid(user);
        User foundUser = userService.login(user.getEmail(), user.getPassword());
        response.addCookie(JwtUtils.generateJwtCookie(foundUser.getEmail()));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset")
    public ResponseEntity<String> reset() {
        userService.reset();
        return ResponseEntity.ok().build();
    }

    private void isUserValid(User user) throws SpringBusinessException {
        if (user == null || user.getEmail() == null || user.getEmail().isBlank()
                || user.getPassword() == null || user.getPassword().isBlank()) {
            throw new SpringBusinessException("Request should contain email and password", HttpStatus.BAD_REQUEST);
        }
    }
}
