package com.example.filemanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.Date;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Configuration;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

public class S3Backend implements StorageBackend {
    private static String BUCKET = "filemanager-application";
    private S3Presigner s3Presigner;

    public S3Backend () {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        this.s3Presigner = S3Presigner.builder()
            .region(region)
            .credentialsProvider(credentialsProvider)
            .build();
    }

    public String getDownloadFileLink (FileDescriptor fd) {
        GetObjectRequest getObjectRequest =
            GetObjectRequest.builder()
                .bucket(S3Backend.BUCKET)
                .key(fd.getFilepath())
                .build();
        GetObjectPresignRequest getObjectPresignRequest =
            GetObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .getObjectRequest(getObjectRequest)
                .build();
        PresignedGetObjectRequest presignedGetObjectRequest =
            this.s3Presigner.presignGetObject(getObjectPresignRequest);

        this.s3Presigner.close();

        URL url = null;
        if (presignedGetObjectRequest.isBrowserExecutable()) {
            url = presignedGetObjectRequest.url();
        } else {
            throw new RuntimeException("The presigned URL has attributes that makes it unexecutable in the browser");
        }

        System.out.println(presignedGetObjectRequest);
        return url.toString();
    }

    public void uploadFile (FileDescriptor fd, InputStream is) {
    }
}
