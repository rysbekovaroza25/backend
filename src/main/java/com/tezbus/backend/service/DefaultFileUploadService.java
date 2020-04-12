package com.tezbus.backend.service;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class DefaultFileUploadService implements FileUploadService {

    @Autowired
    AmazonS3Client amazonS3Client;

    @Value("${s3.bucket.name}")
    String defaultBucketName;

    @Override
    public String uploadFile(String name, MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(multipartFile.getBytes());
        fos.close();
        amazonS3Client.putObject(defaultBucketName, name, file);

        return "https://" + defaultBucketName + ".s3.eu-central-1.amazonaws.com/" + fileName;
    }
}