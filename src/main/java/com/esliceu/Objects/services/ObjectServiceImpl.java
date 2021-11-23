package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.ObjectDAO;
import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ObjectServiceImpl implements ObjectService {

    @Autowired
    ObjectDAO objectDAO;

    @Autowired
    HttpSession session;

    @Autowired
    Utils utils;

    @Override
    public Obj newObject(String bucketUri, String uri, MultipartFile file) {

        System.out.println(file.getOriginalFilename());


        String realuri = "/";
        realuri += bucketUri + "/";
        realuri += uri;

        Obj exists = objectDAO.getObject(realuri);


        Obj obj = new Obj();
        try {
            obj.setUri(realuri);
            obj.setUsername_owner((String) session.getAttribute("username"));
            obj.setContent(file.getBytes());
            if (exists!=null){
                obj.setVersion(exists.getVersion()+1);
            }else {
                obj.setVersion(1);
            }
            obj.setContentLenght(file.getBytes().length);
            obj.setCreatedDate(Date.from(Instant.now()));
            obj.setContentType(utils.getFileExtension(file.getOriginalFilename()));
            obj.setLastModified(Date.from(Instant.now()));
            obj.setHash(String.valueOf(Arrays.hashCode(file.getBytes())));

          boolean res =  objectDAO.newObject(obj.getUri(),
                    obj.getContent(),
                    obj.getVersion(),
                    obj.getContentLenght(),
                    obj.getContentType(),
                    obj.getLastModified(),
                    obj.getCreatedDate(),
                    obj.getHash());

            if (res){
                return obj;
            }
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    public List<Obj> getObjects(){
        return objectDAO.objectsFromUser();
    }

    @Override
    public Obj getObject(String uri) {
        return objectDAO.getObject(uri);
    }

    @Override
    public String getObjName(String uri) {
        Obj obj = objectDAO.getObject(uri);

        String[] name = uri.split("\\/");

        String res = name[name.length-1]+obj.getContentType();

        return res;
    }

}
