package com.example.filemanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.async.AsyncRequestBody;
import software.amazon.awssdk.core.async.BlockingInputStreamAsyncRequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

public class S3Backend implements StorageBackend {
    private static String BUCKET = "filemanager-application";
    private S3Presigner s3Presigner;
    private S3AsyncClient s3AsyncClient;

    public S3Backend () {
        ProfileCredentialsProvider credentialsProvider = ProfileCredentialsProvider.create();
        Region region = Region.US_EAST_1;
        this.s3Presigner = S3Presigner.builder()
            .region(region)
            .credentialsProvider(credentialsProvider)
            .build();
        this.s3AsyncClient = S3AsyncClient.create();
    }

    public String getDownloadFileLink (FileDescriptor fd) {
        GetObjectRequest getObjectRequest =
            GetObjectRequest.builder()
                .bucket(S3Backend.BUCKET)
                .key(fd.getFilepath())
                .responseContentDisposition("attachment; filename=" + fd.getFilename())
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

    public CompletableFuture<PutObjectResponse> uploadFile (FileDescriptor fd, InputStream is, long length) {
        try {
            BlockingInputStreamAsyncRequestBody body = AsyncRequestBody.forBlockingInputStream(null);

            CompletableFuture<PutObjectResponse> responseFuture =
                this.s3AsyncClient.putObject(r ->
                    r.bucket(S3Backend.BUCKET)
                        .key(fd.getFilepath())
                        .contentType(fd.getMimeType())
                        .contentLength(length)
                        .build()
                , body);

            body.writeInputStream(is);

            return responseFuture;
        } catch (S3Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }
}
