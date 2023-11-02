package com.example.filemanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.xml.sax.SAXException;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

@RestController
public class FileUploadController {
    private static String PATH = "/api/fileupload";

    @PostMapping(value = {"/api/fileupload/{filename}"})
    public void upload (HttpServletRequest request, HttpServletResponse response, @PathVariable("filename") String filename) {
        try {
            S3Backend s3 = new S3Backend();

            Part filePart = request.getPart("file");
            String type = filePart.getContentType();
            BufferedReader fileBr = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

            Part pathPart = request.getPart("path");
            String mimeType = getMimeType(pathPart.getInputStream());

            String fullPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
            String filepath = fullPath.substring(FileUploadController.PATH.length());
            FileDescriptor fd = new FileDescriptor(filepath, mimeType);

            long length = filePart.getSize();

            CompletableFuture responseFuture = s3.uploadFile(fd, filePart.getInputStream(), length);
            if (responseFuture != null) {
                response.setStatus(200);
                Object fileUploadResponse = responseFuture.join();
                System.out.println(fileUploadResponse);
            } else {
                response.setStatus(500);
            }
        } catch (Exception ex) {
            System.err.println(ex);
            try {
                response.sendError(500, ex.toString());
            } catch (IOException ioex) {
                System.out.println("Couldn't write to client");
            }
        }
    }

    public String getMimeType (InputStream is) throws IOException, SAXException, TikaException {
        TikaConfig tika = new TikaConfig();

        Metadata metadata = new Metadata();
        MediaType mimetype = tika.getDetector().detect(TikaInputStream.get(is), metadata);

        return mimetype.toString();
    }
}
