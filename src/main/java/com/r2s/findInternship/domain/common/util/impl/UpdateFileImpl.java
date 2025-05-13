package com.r2s.findInternship.domain.common.util.impl;

import java.util.UUID;

import jakarta.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;
import com.r2s.findInternship.domain.common.FileUpload;
import com.r2s.findInternship.domain.common.util.UpdateFile;

@Component
public class UpdateFileImpl implements UpdateFile {
    @Autowired
    ServletContext application;

    @Autowired
    Storage storage;
    @Value("${url.firebase.bucket}")
    String bucketName;
    @Value("${url.firebase.folder}")
    String linkFolder;
    public final static Logger LOGGER = LoggerFactory.getLogger("info");
    public final static String FOLDER_NAME = "images/";

    @EventListener
    public void init(ApplicationReadyEvent event) {
        try {

            ClassPathResource serviceAccount = new ClassPathResource("firebase.json");
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
                    .setStorageBucket(bucketName)
                    .build();

            FirebaseApp.initializeApp(options);

        } catch (Exception ex) {

            ex.printStackTrace();

        }
    }

    @Override
    public void update(FileUpload fileUpload) {
        try {

            UUID uuid = UUID.randomUUID();
            fileUpload.setOutput(uuid +
                    fileUpload.getFile().getOriginalFilename()
                            .substring(fileUpload.getFile().getOriginalFilename().lastIndexOf(".")));

            String name = fileUpload.getOutput();

            byte[] arr = fileUpload.getFile().getBytes();
            Bucket bucket = StorageClient.getInstance().bucket();
            bucket.create(FOLDER_NAME + name, arr, fileUpload.getFile().getContentType());

            fileUpload.setOutput(linkFolder + bucketName + "/o/" + "images%2F"
                    + name + "?alt=media");
            fileUpload.setFile(null);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void uploadCV(FileUpload fileUpload) {
        try {

            UUID uuid = UUID.randomUUID();
            fileUpload.setOutput(uuid +
                    fileUpload.getFile().getOriginalFilename()
                            .substring(fileUpload.getFile().getOriginalFilename().lastIndexOf(".")));

            String name = fileUpload.getOutput();

            byte[] arr = fileUpload.getFile().getBytes();
            Bucket bucket = StorageClient.getInstance().bucket();
            bucket.create(FOLDER_NAME + name, arr, fileUpload.getFile().getContentType());

            fileUpload.setOutput(linkFolder + bucketName + "/o/" + "images%2F"
                    + name + "?alt=media");
            fileUpload.setFile(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void uploadExcel(FileUpload fileUpload) {
        try {

            UUID uuid = UUID.randomUUID();
            fileUpload.setOutput(uuid +
                    fileUpload.getFile().getOriginalFilename()
                            .substring(fileUpload.getFile().getOriginalFilename().lastIndexOf(".")));

            String name = fileUpload.getOutput();

            byte[] arr = fileUpload.getFile().getBytes();
            Bucket bucket = StorageClient.getInstance().bucket();
            bucket.create(FOLDER_NAME + name, arr, fileUpload.getFile().getContentType());

            fileUpload.setOutput(linkFolder + bucketName + "/o/" + "images%2F"
                    + name + "?alt=media");
            fileUpload.setFile(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFile(String fullPath) {
        try {
            String name = fullPath.substring(fullPath.lastIndexOf("%") + 3);
            String pathImg = name.substring(0, name.indexOf("?"));

            System.out.println(pathImg + " " + fullPath.lastIndexOf("%"));
            Bucket bucket = StorageClient.getInstance().bucket(bucketName);

            Blob blob = bucket.get(FOLDER_NAME + pathImg);

            blob.delete();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void uploadCVApplication(FileUpload fileUpload) {
        try {

            UUID uuid = UUID.randomUUID();
            fileUpload.setOutput(uuid +
                    fileUpload.getFile().getOriginalFilename()
                            .substring(fileUpload.getFile().getOriginalFilename().lastIndexOf(".")));

            String name = fileUpload.getOutput();

            byte[] arr = fileUpload.getFile().getBytes();
            Bucket bucket = StorageClient.getInstance().bucket();
            bucket.create(FOLDER_NAME + name, arr, fileUpload.getFile().getContentType());

            fileUpload.setOutput(linkFolder + bucketName + "/o/" + "images%2F"
                    + name + "?alt=media");
            fileUpload.setFile(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}