package com.example.test_ci.S3;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class S3Uploader {

    private final S3Client s3Client;
    private final String bucket;

    public S3Uploader(@Value("${cloud.aws.bucket}") String bucket,
                      @Value("${cloud.aws.region}") String region) {
        this.bucket = bucket;
        this.s3Client = S3Client.builder()
                .region(Region.of(region))
                .credentialsProvider(EnvironmentVariableCredentialsProvider.create())
                .build();
    }

    public String upload(String localPath, String keyName) throws IOException {
        Path path = Paths.get(localPath);
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(keyName)
                .acl(ObjectCannedACL.PUBLIC_READ)
                .contentType("image/png")
                .build();

        s3Client.putObject(request, path);
        return String.format("https://%s.s3.amazonaws.com/%s", bucket, keyName);
    }
}