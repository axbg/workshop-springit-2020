package com.springit.workshop.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties({ "id", "password", "lastReset", "notes" })
public class User extends AuditableEntity implements Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Size(min = 3, max = 70)
    private String email;

    private String password;

    private LocalDateTime lastReset = LocalDateTime.now();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Note> notes;

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