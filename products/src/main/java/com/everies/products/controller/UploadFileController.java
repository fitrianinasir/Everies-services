package com.everies.products.controller;

import com.everies.products.dto.ResMsg;
import com.everies.products.dto.UploadFileRes;
import com.everies.products.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api")
public class UploadFileController {
    @Autowired
    FileStorageService fileStorageService;

    // =========================== UPLOAD FILE ===================================
    @PostMapping("/category/img")
    public UploadFileRes uploadFile(@RequestParam("image") MultipartFile file){
        String fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/img/").path(fileName).toUriString();

        UploadFileRes response = new UploadFileRes();
        response.setFileName(fileName);
        response.setFileDownloadUri(fileDownloadUri);
        return response;
    }


    @GetMapping("/img/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request){
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = null;
        contentType = request.getContentType();

        if(contentType == null){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
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
