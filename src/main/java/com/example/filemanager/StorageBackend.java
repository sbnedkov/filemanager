package com.example.filemanager;

import java.io.InputStream;
import java.util.concurrent.CompletableFuture;

public interface StorageBackend {
    public String getDownloadFileLink(FileDescriptor fd);
    public CompletableFuture<?> uploadFile(FileDescriptor fd, InputStream is, long length);
}
