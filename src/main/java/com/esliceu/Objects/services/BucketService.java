package com.esliceu.Objects.services;

import com.esliceu.Objects.model.Bucket;
import org.springframework.ui.Model;

import java.util.List;

public interface BucketService {
    boolean newBucket(String uri);
    void deleteBucket(String uri);
    Bucket getBucket(String uri);
    List<Bucket> bucketsForUser(String s);
}
