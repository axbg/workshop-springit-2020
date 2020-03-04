package com.springit.workshop.spring.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
public class User implements Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 70)
    private String email;

    private String password;

    private Instant lastReset = Instant.now();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /* Authentication impl */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(final boolean b) throws IllegalArgumentException {
    }

    @Override
    public String getName() {
        return null;
    }
}