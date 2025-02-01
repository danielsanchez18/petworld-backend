package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User saveUser(User user) throws DuplicateResourceException;

    Optional<User> getUserById(UUID idUser);

    List<User> getAllUsers();

    Page<User> getAllUsers(Pageable pageable);

    Page<User> getUsersByFullName(String fullName, Pageable pageable);

    Page<User> getUsersByEmail(String email, Pageable pageable);

    Page<User> getUsersByRole(String role, Pageable pageable);

    Page<User> getUsersByEnabled(boolean enabled, Pageable pageable);

    Long getTotalUsers();

    User updateUser(UUID idUser, User user) throws DuplicateResourceException;

    void deleteUser(UUID idUser);

}
