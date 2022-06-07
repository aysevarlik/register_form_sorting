package com.example.registerformandsorting.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "register")

public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String lastName;

    @Column(name = "mail")
    private String mail;

    @Column(name = "password")
    private String password;

}
