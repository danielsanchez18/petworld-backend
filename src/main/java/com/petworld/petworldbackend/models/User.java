package com.petworld.petworldbackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id_user")
    private UUID idUser;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id_role", nullable = false)
    @JsonIgnore
    private Role role;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String lastname;

    @Column(length = 9, nullable = false)
    private String phone;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 200)
    private String address;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(nullable = false)
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convertir el rol del usuario en una autoridad (ej: "ROLE_ADMIN")
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.getName()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}