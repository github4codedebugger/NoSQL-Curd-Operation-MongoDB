package com.md.example.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "employee")
public class Employee {

    @Id
    private Integer id;

    private String name;

    private double salary;
}

