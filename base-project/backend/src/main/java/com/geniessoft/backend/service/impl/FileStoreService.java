package com.geniessoft.backend.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.geniessoft.backend.utility.bucket.BucketName;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

@Service
@Slf4j
public class FileStoreService {

    private final AmazonS3 amazonS3;

    public FileStoreService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void upload(String path, String filename,
            Optional<Map<String,String>> optionalMetadata,
            InputStream inputStream){

        ObjectMetadata metadata = new ObjectMetadata();
        optionalMetadata.ifPresent(map->{
            if(!map.isEmpty()){
                map.forEach(metadata::addUserMetadata);
            }
        });

        try{
            amazonS3.putObject(path,filename,inputStream,metadata);
        }
        catch (AmazonServiceException exception){
            throw new IllegalStateException("Failed to store file to S3",exception);
        }
    }

    public byte[] download(String path, String fileName) {
        log.info("path : "+path);
        log.info("filename : "+fileName);
        try {
            S3Object object = amazonS3.getObject(path,fileName);
            S3ObjectInputStream inputStream = object.getObjectContent();
            return IOUtils.toByteArray(inputStream);
        }
        catch (AmazonServiceException | IOException e){
            throw new IllegalStateException("Failed to download file to S3",e);
        }
    }

    public void delete(String path, String filename){
        amazonS3.deleteObject(path,filename);
    }

    public Map<String,String> getMetadata(MultipartFile file){
        Map<String,String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length",String.valueOf(file.getSize()));
        return metadata;
    }

    public String getPreSignedDownloadUrl(String bucketName, String fileName) {
        log.info("bucketName : "+bucketName);
        log.info("filename : "+fileName);
        bucketName = "upisland-bucket/user_profile_images"; //!!!!!!!!!!!!!!
        java.util.Date expiration = new java.util.Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 60; //1 HR expiration time
        expiration.setTime(expTimeMillis);
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucketName, fileName)
                        .withMethod(HttpMethod.GET)
                        .withExpiration(expiration);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        log.info("Pre-signed URL found for " + fileName);
        return url.toString();
    }
}
