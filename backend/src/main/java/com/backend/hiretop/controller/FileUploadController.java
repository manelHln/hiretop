package com.backend.hiretop.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.hiretop.dto.ResponseVO;
import com.backend.hiretop.dto.ResponseVOBuilder;
import com.backend.hiretop.service.FileStorageService;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("files")
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    private final Path rootLocation = Paths.get("upload_dir");

    @PermitAll
    @GetMapping("/{filename}")
    public ResponseEntity<String> serveFile(@PathVariable String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = "application/octet-stream";
                try {
                    contentType = Files.probeContentType(file);
                } catch (Exception e) {
                    // If we can't determine the file's type, the default content type will be used.
                }

                String base64String = Base64.getEncoder().encodeToString(resource.getContentAsByteArray());

                return ResponseEntity.ok().body(base64String);

                // return ResponseEntity.ok()
                //         .contentType(MediaType.parseMediaType(contentType))
                //         .header(HttpHeaders.CONTENT_DISPOSITION,
                //                 "inline; filename=\"" + resource.getFilename() + "\"")
                //         .body(resource.getContentAsByteArray());
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (Exception e) {
            throw new RuntimeException("Could not serve file: " + filename, e);
        }
    }

    @PostMapping(name = "/upload" ,consumes = "multipart/form-data")
    public ResponseEntity<ResponseVO<String>> uploadFile(@RequestParam MultipartFile file) {
        return ResponseEntity
                .ok(new ResponseVOBuilder<String>().success().addData(fileStorageService.store(file)).build());
    }
}
