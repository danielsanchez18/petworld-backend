package com.petworld.petworldbackend.services;

import com.petworld.petworldbackend.models.JwtResponse;
import com.petworld.petworldbackend.models.Login;
import com.petworld.petworldbackend.models.User;
import com.petworld.petworldbackend.models.UserResponse;

public interface AuthService {

    JwtResponse authenticateUser(Login login);

    UserResponse getCurrentUser();

}