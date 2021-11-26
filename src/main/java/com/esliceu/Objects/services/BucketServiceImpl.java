package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.BucketDAO;
import com.esliceu.Objects.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BucketServiceImpl implements BucketService{

    @Autowired
    HttpSession session;

    @Autowired
    BucketDAO bucketDAO;

    @Override
    public boolean newBucket(String uri) {

        if (uri.contains("/")){
            session.setAttribute("message","El nom no pot contenir el caràcter '/'");
            return false;
        }

        if (getBucket(uri) != null){
            session.setAttribute("message","El nom ja existeix");
            return false;
        }

        bucketDAO.createBucket(uri);
        return true;
    }

    @Override
    public void deleteBucket(String uri) {
        bucketDAO.deleteBucket(uri);
    }

    @Override
    public Bucket getBucket(String uri) {
        return bucketDAO.getBucket(uri);
    }

    @Override
    public List<Bucket> bucketsForUser(String s) {
        return bucketDAO.getForUser(s);
    }


}