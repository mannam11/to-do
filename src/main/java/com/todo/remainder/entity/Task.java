package com.todo.remainder.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int task_id;

    @Column( nullable = false ,name = "user_id")
    private int userId;

    @Column(name = "title")
    @NotBlank(message = "Title can't be empty")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "created_on")
    @CreationTimestamp
    private Date createdOn;

    @Column(name = "deadline")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(value = TemporalType.DATE)
    private Date toBeComplete;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority")
    private Priority priority;

}
