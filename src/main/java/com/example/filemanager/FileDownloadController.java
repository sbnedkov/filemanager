package com.example.filemanager;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileDownloadController {
    private static String PATH = "/api/filedownload";

    @GetMapping(value = {"/api/filedownload/**"})
    public String download (HttpServletRequest request, HttpServletResponse response) {
        S3Backend s3 = new S3Backend();
        String fullPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String filepath = fullPath.substring(FileDownloadController.PATH.length());

        response.setHeader("Content-Disposition", "inline");
        return s3.getDownloadFileLink(new FileDescriptor(filepath));
    }
}
