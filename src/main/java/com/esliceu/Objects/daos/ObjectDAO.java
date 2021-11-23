package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;

import java.util.Date;
import java.util.List;

public interface ObjectDAO {

    boolean newObject(String uri, byte[] content, int version, int contentLength,String contentType,Date lastModified, Date createdDate,String hash);
    Obj getObject(String uri);
    List<Obj> objectsFromUser();
}
