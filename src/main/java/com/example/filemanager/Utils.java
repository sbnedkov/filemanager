package com.example.filemanager;

public class Utils {
    public static FileDescriptor pathToFileDescriptor (String filepath) {
        return new FileDescriptor(filepath);
    }
}
