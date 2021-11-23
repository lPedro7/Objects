package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;

import java.util.Date;
import java.util.List;

public interface ObjectDAO {

    boolean newObject(String uri,String bucketUri, byte[] content, int version, int contentLength,String contentType,Date lastModified, Date createdDate,String hash);
    Obj getObject(String uri,String bucket);
    List<Obj> objectsFromUser();
    List<Obj> objectsFromBucket(String bucket);

    void deleteObject(String bucket, String obj);
}
