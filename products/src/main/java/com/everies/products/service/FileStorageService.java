package com.everies.products.service;

import com.everies.products.configuration.FileStorageProperties;
import com.everies.products.exception.FileNotFoundException;
import com.everies.products.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class FileStorageService {
    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        System.out.println("1. File storage location is = " + fileStorageLocation);
        try {
            if(!Files.exists(this.fileStorageLocation)){
                Files.createDirectories(this.fileStorageLocation);
            }
        }catch (Exception ex){
            throw new FileStorageException("Could not create the directory where the upload files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String timestamp = dateFormat.format(new Date());

        Random random = new Random();
        int randomInt = random.nextInt(10000); // Adjust the range as needed

        String realFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = timestamp +"_"+randomInt+".jpeg";
        try {
            if(fileName.contains("..")){
                throw new FileStorageException("File name contains invalid path " + realFileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return fileName;
        }catch (IOException ex){
            throw new FileStorageException("Could not store file " + fileName, ex);
        }
    }

    public boolean deleteFile(String fileName) {
        Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
        try{
            if(Files.exists(filePath)){
                Files.delete(filePath);
            }else{
                return false;
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File is not found",e);
        }
        return true;
    }

    public File loadFileAsResource(String fileName){
        File serverFile = new File(this.fileStorageLocation.resolve(fileName).normalize().toUri());
        return serverFile;
    }

}
