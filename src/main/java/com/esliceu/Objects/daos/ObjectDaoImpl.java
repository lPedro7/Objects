package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Repository
public class ObjectDaoImpl implements ObjectDAO{

    @Autowired
    HttpSession session;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean newObject(String uri,String bucketUri, byte[] content, int version, int contentLength,String contentType,Date lastModified, Date createdDate,String hash) {

        String sql = "INSERT INTO Object VALUES(?,?,?,?,?,?,?,?,?,?)";

        String username = (String) session.getAttribute("username");

        int ok = jdbcTemplate.update(sql,uri,bucketUri,username,content,version,contentLength,contentType,lastModified,createdDate,hash);

        if (ok==1) return true;

        return false;
    }

    @Override
    public Obj getObject(String bucket,String obj) {

        List<Obj> objs = jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=? AND uri =?",new BeanPropertyRowMapper<Obj>(Obj.class),bucket,obj);

        if (objs.size()>0){
            return objs.get(objs.size()-1);
        }else return null;

    }

    @Override
    public List<Obj> objectsFromUser() {
        return jdbcTemplate.query("SELECT * FROM Object WHERE username_owner=?",new BeanPropertyRowMapper<Obj>(Obj.class),(String) session.getAttribute("username"));
    }

    @Override
    public List<Obj> objectsFromBucket(String bucket) {
        return jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=?",new BeanPropertyRowMapper<Obj>(Obj.class),bucket);
    }

    @Override
    public void deleteObject(String bucket, String obj) {
        jdbcTemplate.update("DELETE FROM Object WHERE uri=? AND bucketUri=?",obj,bucket);
    }
}
