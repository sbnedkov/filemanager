package com.example.filemanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {
    private @Id @GeneratedValue Long id;
    @Column(nullable = false, unique = true, length = 255)
    private String username;
    @Column(nullable = false, length = 31)
    private String authority;

    private Authority () {
    }

    public Authority (String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getUsername () {
        return this.username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getAuthority () {
        return this.authority;
    }

    public void setAuthority (String authority) {
        this.authority = authority;
    }

    @Override
    public String toString () {
        return "Authority { id: " + this.id + ", username: " + this.username + ", authority: " + this.authority + "}";
    }
}
