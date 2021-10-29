package com.geniessoft.backend.utility.bucket;

public enum BucketName {

    PROFILE_IMAGE("upisland-bucket");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
