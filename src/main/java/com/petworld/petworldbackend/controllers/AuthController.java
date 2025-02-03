package com.petworld.petworldbackend.controllers;

import com.petworld.petworldbackend.models.JwtResponse;
import com.petworld.petworldbackend.models.Login;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.models.UserResponse;
import com.petworld.petworldbackend.services.AuthService;
import com.petworld.petworldbackend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody Login request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        String token = jwtUtils.generateToken((UserDetails) authentication.getPrincipal());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserResponse> currentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }

}
