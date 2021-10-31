package com.geniessoft.backend.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.geniessoft.backend.utility.bucket.BucketName;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
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
}
