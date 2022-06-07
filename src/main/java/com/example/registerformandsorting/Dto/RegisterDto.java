package com.example.registerformandsorting.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegisterDto {
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotEmpty(message = "last name cannot be empty")
    private String lastName;

    @NotEmpty(message = "mail cannot be empty")
    @Email(message = "email not entered correctly")
    private String mail;

    @NotEmpty(message = "password cannot be empty")
    private String password;
}
