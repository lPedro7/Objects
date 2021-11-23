package com.esliceu.Objects.services;

import com.esliceu.Objects.model.Obj;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface ObjectService {

    Obj newObject(String bucketUri,String uri, MultipartFile file);

    List<Obj> getObjects();

    Obj getObject(String uri,String bucket);

    String getObjName(String uri,String bucket);

    List<Obj> objectsFromBucket(String bucket);
}
