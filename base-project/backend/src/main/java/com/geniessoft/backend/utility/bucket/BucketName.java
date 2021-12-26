package com.geniessoft.backend.utility.bucket;

public enum BucketName {

    BUCKET_NAME("upisland");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }

}
