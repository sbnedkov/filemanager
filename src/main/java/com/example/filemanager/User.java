package com.example.filemanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class User {
    private @Id @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String salt;
    private String passwordHash;

    private User () {
    }

    public User (String firstName, String lastName, String email, String passwordHash) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getFirstName () {
        return this.firstName;
    }

    public void setFirstName (String firstName) {
        this.firstName = firstName;
    }
 
    public String getLastName () {
        return this.lastName;
    }

    public void setLastName (String lastName) {
        this.lastName = lastName;
    }
 
    public String getEmail () {
        return this.email;
    }

    public void setEmail (String email) {
        this.email = email;
    }
 
    public String getSalt () {
        return this.salt;
    }

    public void setSalt (String salt) {
        this.salt = salt;
    }

    public String getPasswordHash () {
        return this.passwordHash;
    }

    public void setPasswordHash (String passwordHash) {
        this.passwordHash = passwordHash;
    }

    @Override
    public String toString () {
        return "User { id: " + this.id + ", firstName: " + this.firstName + ", lastName: " + this.lastName + ", email: " + this.email + "}";
    }
}
