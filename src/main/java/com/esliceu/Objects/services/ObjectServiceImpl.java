package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.ObjectDAO;
import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.utils.Utils;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        System.out.println("service");
        System.out.println(uri);

        if (uri.charAt(0)!='/'){
            uri = "/"+uri;
        }

        Obj obj = new Obj();
        try {
            obj.setName(file.getOriginalFilename());
            obj.setUri(uri);
            obj.setBucketUri(bucketUri);
            obj.setUsername_owner((String) session.getAttribute("username"));
            obj.setContent(file.getBytes());
            obj.setCreatedDate(Date.from(Instant.now()));
            obj.setContentLength(file.getBytes().length);
            obj.setContentType(utils.getFileExtension(file.getOriginalFilename()));
            obj.setHash(String.valueOf(Arrays.hashCode(file.getBytes())));

          boolean res =  objectDAO.newObject(obj.getName(),
                  obj.getUri(),
                    obj.getBucketUri(),
                    obj.getContent(),
                    obj.getContentLength(),
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
    public Obj getObject(String bucket,String object) {
        return objectDAO.getObject(bucket,object);
    }

    @Override
    public String getObjName(String bucket,String object) {
        Obj obj = objectDAO.getObject(bucket,object);

        return obj.getName();
    }

    @Override
    public List<Obj> objectsFromBucket(String bucket) {
        return objectDAO.objectsFromBucket(bucket);
    }

    @Override
    public void deleteObject(String bucket, String obj,int version) {
        objectDAO.deleteObject(bucket,obj,version);
    }

    @Override
    public List<String> getFolderPath(String bucket, String obj) {
        return objectDAO.getUri(bucket,obj);
    }

    @Override
    public String firstPath(String s) {
        Pattern pattern = Pattern.compile("[/]+\\w+");

        System.out.println(s);

        Matcher matcher = pattern.matcher(s);

        List<String> res = new ArrayList<>();

        while (matcher.find())res.add(matcher.group());

        return res.get(0);
    }

    @Override
    public void download(HttpServletResponse resp,String bucket, String obj,int version) {

        Obj object = getObject(bucket, obj);

        File f = new File(getObjName(bucket, obj));

        try {
            Files.write(object.getContent(), f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-disposition", "attachment; filename="+getObjName(bucket,obj));


        try(InputStream in = new FileInputStream(f);
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[object.getContentLength()];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    @Override
    public List<Obj> getAllVersions(String bucket, String obj) {
        return objectDAO.getAllVersions(bucket,obj);
    }

}
