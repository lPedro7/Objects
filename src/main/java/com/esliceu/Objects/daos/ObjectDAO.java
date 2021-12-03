package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;

import java.util.Date;
import java.util.List;

public interface ObjectDAO {
    boolean newObject(String name,String uri,String bucketUri, byte[] content, int contentLength,String contentType,Date lastModified, Date createdDate,String hash, int version);
    Obj getObject(int id);
    Obj getObject(String uri,String bucket);
    Obj getObject(String uri,String bucket,int version);
    List<Obj> getAllVersions(String bucket, String obj);
    List<Obj> objectsFromUser();
    List<Obj> objectsFromBucket(String bucket);
    void deleteObject(String bucket, String obj,int version);
    void deleteObject(String bucket, String uri);
    void deleteObject(int id);
    List<String> getUri(String bucket, String obj);
}
