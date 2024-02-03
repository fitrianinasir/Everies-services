package com.everies.products.controller;

import com.everies.products.dto.ResMsg;
import com.everies.products.dto.UploadFileRes;
import com.everies.products.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class UploadFileController {
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    private ResourceLoader resourceLoader;


    // Method to clear classpath resource cache
    // =========================== UPLOAD FILE ===================================
    @PostMapping("/category/img")
    public UploadFileRes uploadFile(@RequestParam("image") MultipartFile file){
        String subfolder = "/categories";
        String fileName = fileStorageService.storeFile(file, subfolder);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/").path(fileName).toUriString();

        UploadFileRes response = new UploadFileRes();
        response.setFileName(fileName);
        response.setFileDownloadUri(fileDownloadUri);

        return response;
    }

    // =========================== UPLOAD FILE ===================================
    @PostMapping("/product/img")
    public UploadFileRes uploadProductImg(@RequestParam("image") MultipartFile file){
        String subfolder = "/products";
        String fileName = fileStorageService.storeFile(file, subfolder);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/").path(fileName).toUriString();

        UploadFileRes response = new UploadFileRes();
        response.setFileName(fileName);
        response.setFileDownloadUri(fileDownloadUri);

        return response;
    }

    @GetMapping("/image/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) {
        try {
            File serverFile = fileStorageService.loadFileAsResource(imageName);
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Adjust based on your image type
                        .body( Files.readAllBytes(serverFile.toPath()));

        } catch (Exception e) {
            System.out.println("Img not found");
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/img/{fileName:.+}")
    public ResMsg deleteFile(@PathVariable String fileName){
        Boolean result = fileStorageService.deleteFile(fileName);
        ResMsg response = new ResMsg<>();
        if(result){
            response.setStatus(200);
            response.setMessage("File deleted successfully");
            response.setData(null);
        }else{
            response.setStatus(400);
            response.setMessage("Failed to delete file");
            response.setData(null);
        }
        return response;
    }

}
