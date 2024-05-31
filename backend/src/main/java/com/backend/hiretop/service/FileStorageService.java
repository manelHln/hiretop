package com.backend.hiretop.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileStorageService {
    private final Path rootLocation = Paths.get("upload_dir");

    public String store(MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            String originalFilename = file.getOriginalFilename();
            String fileExtension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
            }
            String currentTimestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String newFilename = UUID.randomUUID().toString() + "_" + currentTimestamp + fileExtension;

            Path destinationFile = this.rootLocation.resolve(
                    Paths.get(newFilename))
                    .normalize().toAbsolutePath();
            if (!destinationFile.getParent().equals(this.rootLocation.toAbsolutePath())) {
                throw new RuntimeException("Cannot store file outside current directory.");
            }
            file.transferTo(destinationFile);
            return newFilename;
        } catch (Exception e) {
            System.out.println(e.getCause());
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}