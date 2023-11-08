package com.example.filemanager;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "files")
public class File {
    private static int FILE = 1;
    private static int DIR = 2;

    private @Id @GeneratedValue Long id;
    @Column(nullable = false, unique = true, length = 4096)
    private String name;
    @Column(nullable = false)
    private int type;

    private File () {
    }

    public File (String name, int type) {
        this.name = name;
        this.type = type;
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

    public int getType () {
        return type;
    }

    public void setType (int type) {
        this.type = type;
    }

    @Override
    public String toString () {
        return "File { id: " + this.id + ", name: " + this.name + ", type: " + this.type + " }";
    }
}
