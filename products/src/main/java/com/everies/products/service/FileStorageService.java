package com.everies.products.service;

import com.everies.products.configuration.FileStorageProperties;
import com.everies.products.exception.FileNotFoundException;
import com.everies.products.exception.FileStorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println("1. File name is " + fileName);
        try {
            if(fileName.contains("..")){
                throw new FileStorageException("File name contains invalid path " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            System.out.println("2. target Location with file name is:" + targetLocation);
            System.out.println("3. get input stream of file => " + file.getInputStream().toString());
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
                System.out.println("file exist and will be deleted");
                Files.delete(filePath);
            }else{
                return false;
            }
        } catch (IOException e) {
            throw new FileNotFoundException("File is not found",e);
        }
        return true;
    }

    public Resource loadFileAsResource(String fileName){
        try{
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            System.out.println("4. file path get from fileStorgeLocation normalize => " + filePath);
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()){
                return resource;
            }else{
                throw new FileStorageException("File not found " + fileName);
            }
        }catch (MalformedURLException ex){
            throw new FileNotFoundException("File not found " + fileName, ex);
        }
    }

}
