package com.example.filemanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileUploadController {
    @PostMapping(value = {"/api/fileupload/{filename}"})
    public void upload (HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) {
        try {
            Part filePart = request.getPart("file");
            String type = filePart.getContentType();
            BufferedReader fileBr = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

            Part pathPart = request.getPart("path");
            System.out.println(pathPart);
            BufferedReader pathBr = new BufferedReader(new InputStreamReader(pathPart.getInputStream()));
            String path = pathBr.readLine();

            System.out.println("FILENAME: " + filename);
            System.out.println("FILETYPE: " + type);
            System.out.println("FILEPATH: " + path);
            // fileBr.lines().forEach(line -> System.out.println(line));
        } catch (Exception ex) {
            System.err.println(ex);
            try {
                response.sendError(500, ex.toString());
            } catch (IOException ioex) {
                System.out.println("Couldn't write to client");
            }
        }
    }
}
