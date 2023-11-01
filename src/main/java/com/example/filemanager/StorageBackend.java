package com.example.filemanager;

import java.io.InputStream;

public interface StorageBackend {
    public String getDownloadFileLink(FileDescriptor fd);
    public void uploadFile(FileDescriptor fd, InputStream is);
}
