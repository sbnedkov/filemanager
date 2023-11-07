package com.example.filemanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
    private @Id @GeneratedValue Long id;
    // @Column(length = 255)
    // private String firstName;
    // @Column(length = 255)
    // private String lastName;
    @Column(nullable = false, unique = true, length = 255)
    private String username;
    @Column(nullable = false, length = 255)
    private String password;
    @Column(nullable = false)
    private Boolean enabled;

    private UserEntity () {
    }

    public UserEntity (String username) {
        this.username = username;
    }

    public UserEntity (/* String firstName, String lastName, */String username, String password, boolean enabled) {
        // this.firstName = firstName;
        // this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    // public String getFirstName () {
    //     return this.firstName;
    // }

    // public void setFirstName (String firstName) {
    //     this.firstName = firstName;
    // }
 
    // public String getLastName () {
    //     return this.lastName;
    // }

    // public void setLastName (String lastName) {
    //     this.lastName = lastName;
    // }
 
    public String getUsername () {
        return this.username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getPassword () {
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled (Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString () {
        // return "User { id: " + this.id + ", firstName: " + this.firstName + ", lastName: " + this.lastName + ", username: " + this.username + "}";
        return "User { id: " + this.id + ", username: " + this.username + "}";
    }
}
