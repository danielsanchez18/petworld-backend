package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.exceptions.DuplicateResourceException;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.repositories.UserRepository;
import com.petworld.petworldbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private void validateUserUniqueness(String email, String phoneNumber) throws DuplicateResourceException {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("El correo electrónico ya está registrado");
        }
        if (userRepository.existsByPhone(phoneNumber)) {
            throw new DuplicateResourceException("El número telefónico ya está registrado");
        }
    }

    @Override
    public User saveUser(User user) throws DuplicateResourceException {
        validateUserUniqueness(user.getEmail(), user.getPhone());
        user.setCreatedAt(new Date());
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(UUID idUser) {
        return userRepository.findById(idUser);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Page<User> getUsersByFullName(String fullName, Pageable pageable) {
        return userRepository.findByFullName(fullName, pageable);
    }

    @Override
    public Page<User> getUsersByEmail(String email, Pageable pageable) {
        return userRepository.findByEmailContainingIgnoreCase(email, pageable);
    }

    @Override
    public Page<User> getUsersByRole(String role, Pageable pageable) {
        return userRepository.findByRole_Name(role, pageable);
    }

    @Override
    public Page<User> getUsersByEnabled(boolean enabled, Pageable pageable) {
        return userRepository.findByEnabled(enabled, pageable);
    }

    @Override
    public Long getTotalUsers() {
        return userRepository.count();
    }

    @Override
    public User updateUser(UUID idUser, User user) throws DuplicateResourceException {

        User existingUser = userRepository.findById(idUser).orElseThrow(
                () -> new RuntimeException("Usuario no encontrado")
        );

        if (!existingUser.getEmail().equals(user.getEmail()) && !existingUser.getPhone().equals(user.getPhone())) {
            validateUserUniqueness(user.getEmail(), user.getPhone());
        }

        existingUser.setName(user.getName());
        existingUser.setLastname(user.getLastname());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setPassword(user.getPassword());
        existingUser.setUpdatedAt(new Date());
        existingUser.setEnabled(user.isEnabled());

        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(UUID idUser) {
        if (!userRepository.existsById(idUser)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        userRepository.deleteById(idUser);
    }
}
