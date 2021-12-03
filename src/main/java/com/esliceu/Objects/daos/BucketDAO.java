package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Bucket;

import java.util.List;

public interface BucketDAO {
    void createBucket(String uri);
    Bucket getBucket(String uri);
    void deleteBucket(String uri);
    List<Bucket> getForUser(String s);
}
