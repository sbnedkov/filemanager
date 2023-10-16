package com.example.filemanager;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;
import java.util.HashMap;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tika.config.TikaConfig;
import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.HttpHeaders;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MediaType;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;

@RestController
public class FileDownloadController {
    static String PATH = "/api/filedownload";

    @GetMapping(value = {"/api/filedownload/**"}, produces="application/octet-stream")
    public String download (HttpServletRequest request, HttpServletResponse response) {
        String fullPath = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        String filepath = fullPath.substring(FileDownloadController.PATH.length());

        int idx = filepath.lastIndexOf('/');
        String filename = filepath.substring(idx + 1);

        try {
            response.setHeader("Content-Type", getMimeType(filepath));
        } catch (Exception e) {
            System.err.println(e);
        }

        response.setHeader("Content-Disposition", "inline");
        return filepath;
    }

    public String getMimeType (String filename) throws FileNotFoundException, IOException, SAXException, TikaException {
        TikaConfig tika = new TikaConfig();

        Metadata metadata = new Metadata();
        Path path = FileSystems.getDefault().getPath(filename);
        MediaType mimetype = tika.getDetector().detect(TikaInputStream.get(path, metadata), metadata);

        return mimetype.toString();
    }
}