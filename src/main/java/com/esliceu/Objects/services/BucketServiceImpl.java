package com.esliceu.Objects.services;

import com.esliceu.Objects.daos.BucketDAO;
import com.esliceu.Objects.model.Bucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Service
public class BucketServiceImpl implements BucketService{

    @Autowired
    HttpSession session;

    @Autowired
    BucketDAO bucketDAO;


    @Override
    public boolean newBucket(Model m,String uri) {

        if (uri.contains("/")){
            m.addAttribute("message","El nom no pot contenir el caràcter '/'");
            return false;
        }

        if (getBucket(uri) != null){
            m.addAttribute("message","El nom ja existeix");
            return false;
        }

        bucketDAO.createBucket(uri.toLowerCase(),(String)session.getAttribute("username"));
        return true;
    }

    @Override
    public void deleteBucket(String uri) {
        bucketDAO.removeBucketByUri(uri);
    }

    @Override
    public Bucket getBucket(String uri) {
        return bucketDAO.getBucketByUriAndUsernameOwner(uri,(String) session.getAttribute("username"));
    }

    @Override
    public List<Bucket> bucketsForUser(String s) {
        return bucketDAO.getBucketsByUsernameOwner(s);
    }


}
