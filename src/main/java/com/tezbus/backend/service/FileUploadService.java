package com.tezbus.backend.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    String uploadFile(String name, MultipartFile multipartFile) throws IOException;
}
