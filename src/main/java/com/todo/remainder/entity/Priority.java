package com.todo.remainder.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "priorities")
@Data
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

}