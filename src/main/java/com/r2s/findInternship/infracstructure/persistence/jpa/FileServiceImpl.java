package com.r2s.findInternship.infracstructure.persistence.jpa;

import com.r2s.findInternship.domain.constant.FolderConstant;
import com.r2s.findInternship.domain.service.FileService;
import com.r2s.findInternship.infracstructure.exception.exception_v2.ResourceNotFoundExceptionV2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Year;
import java.util.Collections;

@Service
public class FileServiceImpl implements FileService {

    @Value("${file.path}")
    private String UPLOAD_DIR;

    @Override
    public String uploadFile(MultipartFile file) {
        if (file == null || file.isEmpty() || file.getOriginalFilename() == null) {
            return null;
        }

        String originalFileName = file.getOriginalFilename();
        String currentYear = String.valueOf(Year.now().getValue());
        String fileExtension = getFileExtension(originalFileName).toLowerCase();
        String subFolder = determineFolder(fileExtension);

        String uploadDir = Paths.get(UPLOAD_DIR, subFolder, currentYear).toString();
        new File(uploadDir).mkdirs();

        String fileName = System.currentTimeMillis() + "_" + originalFileName;
        Path targetPath = Paths.get(uploadDir, fileName);

        try {
            Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return fileName;
    }

    @Override
    public Resource downloadFile(String fileName) {
        String currentYear = String.valueOf(Year.now().getValue());

        for (String subFolder : FolderConstant.ALL_FOLDERS) {
            Path filePath = Paths.get(UPLOAD_DIR, subFolder, currentYear, fileName);
            try {
                Resource resource = new UrlResource(filePath.toUri());
                if (resource.exists() && resource.isReadable()) {
                    return resource;
                }
            } catch (MalformedURLException ignored) {
            }
        }

        throw new ResourceNotFoundExceptionV2(Collections.singletonMap("fileName", fileName));

    }

    @Override
    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            System.out.println("Error: File name is null or empty, skipping deletion.");
            return;
        }

        String currentYear = String.valueOf(Year.now().getValue());

        for (String subFolder : FolderConstant.ALL_FOLDERS) {
            Path filePath = Paths.get(UPLOAD_DIR, subFolder, currentYear, fileName);

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                System.out.println("Error deleting file: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private String getFileExtension(String fileName) {
        int lastIndex = fileName.lastIndexOf('.');
        return (lastIndex != -1 && lastIndex < fileName.length() - 1) ? fileName.substring(lastIndex + 1) : "";
    }

    private String determineFolder(String fileExtension) {
        if (FolderConstant.EXCEL_EXTENSIONS.contains(fileExtension)) {
            return FolderConstant.EXCEL_FOLDER;
        } else if (FolderConstant.IMAGE_EXTENSIONS.contains(fileExtension)) {
            return FolderConstant.IMAGE_FOLDER;
        } else {
            return FolderConstant.CV_FOLDER;
        }
    }
}