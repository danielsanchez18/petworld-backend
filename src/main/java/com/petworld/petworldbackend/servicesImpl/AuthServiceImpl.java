package com.petworld.petworldbackend.servicesImpl;

import com.petworld.petworldbackend.models.JwtResponse;
import com.petworld.petworldbackend.models.Login;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.models.UserResponse;
import com.petworld.petworldbackend.repositories.UserRepository;
import com.petworld.petworldbackend.services.AuthService;
import com.petworld.petworldbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public JwtResponse authenticateUser(Login loginRequest) {
        // Autenticar al usuario
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Generar token JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());
        final String token = jwtUtils.generateToken(userDetails);

        return new JwtResponse(token);
    }

    @Override
    public UserResponse getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(
                user.getIdUser(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getPhone(),
                user.getAddress(),
                user.isEnabled(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getRole().getName()
        );
    }
}