package com.rined.crud.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "user_info")
@Entity(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "company")
    private String company;

    @Column(name = "about")
    private String about;

    public User(String firstName,
                String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
