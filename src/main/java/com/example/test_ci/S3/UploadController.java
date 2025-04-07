package com.example.test_ci.S3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadController {

    private final S3Uploader uploader;

    @GetMapping("/upload")
    public ResponseEntity<String> uploadImage() throws IOException {
        String localImagePath = "assets/image.png"; // Git에 있는 파일
        String s3Key = "uploads/image.png";
        String url = uploader.upload(localImagePath, s3Key);
        return ResponseEntity.ok(url);
    }
}