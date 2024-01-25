package com.todo.remainder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "user_id")
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "FirstName should not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "LastName should not be empty")
    private String lastName;

    @Column(name = "email")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "password should not be empty")
    private String password;
}
