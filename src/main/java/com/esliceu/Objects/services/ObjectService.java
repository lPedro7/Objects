package com.esliceu.Objects.services;

import com.esliceu.Objects.model.Obj;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ObjectService {

    Obj newObject(String bucketUri,String uri, MultipartFile file);

    List<Obj> getObjects();

    Obj getObject(String uri,String bucket);

    String getObjName(String uri,String bucket);

    List<Obj> objectsFromBucket(String bucket);

    void deleteObject(String bucket, String obj);

    List<String> getFolderPath(String bucket, String obj);

    String firstPath(String s);

    void download(HttpServletResponse resp, String bucket, String uri);


    List<Obj> getAllVersions(String bucket, String obj);
}