package com.petworld.petworldbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private UUID idUser;

    private String name;

    private String lastname;

    private String email;

    private String phone;

    private String address;

    private boolean enabled;

    private Date createdAt;

    private Date updatedAt;

    private String role;

}