package com.example.filemanager;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Dir {
    private @Id @GeneratedValue Long id;
    private String name;

    private Dir () {
    }

    public Dir (String name) {
        this.name = name;
    }

    public Long getId () {
        return id;
    }

    public void setId (Long id) {
        this.id = id;
    }

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    @Override
    public String toString () {
        return "Directory { id: " + this.id + ", name: " + this.name + " }";
    }
}
