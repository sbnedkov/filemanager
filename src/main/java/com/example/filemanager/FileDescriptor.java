package com.example.filemanager;

public class FileDescriptor {
    private String owner;
    private String filepath;
    private String filename;
    private String mimeType;

    public FileDescriptor (String filepath) {
        int idx = filepath.lastIndexOf('/');
        this.filepath = filepath.substring(0, idx);
        this.filename = filepath.substring(idx + 1);
        this.mimeType = "application/octet-stream";
    }

    public FileDescriptor (String owner, String path, String name, String mime) {
        this.owner = owner;
        this.filepath = path;
        this.filename = name;
        this.mimeType = mime;
    }

    public String getFilename () {
        return this.filename;
    }

    public String getFilepath () {
        return this.filepath + this.filename;
    }

    public String getMimeType () {
        return this.mimeType;
    }
}
