package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ObjectDaoImpl implements ObjectDAO {

    @Autowired
    HttpSession session;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public boolean newObject(String name,String uri, String bucketUri, byte[] content, int contentLength, String contentType, Date lastModified, Date createdDate, String hash, int version) {

        name= Utils.unaccent(name);
        uri=Utils.unaccent(uri);

        String sql = "INSERT INTO Object(name,uri,bucketUri,username_owner,content,contentLength,contentType,lastModified,createdDate,hash,version) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        String username = (String) session.getAttribute("username");

        int ok = jdbcTemplate.update(sql,name, uri, bucketUri, username, content, contentLength, contentType, lastModified, createdDate, hash,version);

        return ok == 1;
    }

    @Override
    public Obj getObject(int id) {
        List<Obj> obj = jdbcTemplate.query("SELECT * FROM Object WHERE id=?",new BeanPropertyRowMapper<Obj>(Obj.class),id);
        if (obj.size()>0){
            return obj.get(0);
        }
        return null;
    }

    @Override
    public Obj getObject(String bucket, String obj) {

        List<Obj> objs = jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=? AND uri =?", new BeanPropertyRowMapper<Obj>(Obj.class), bucket, obj);

        if (objs.size() > 0) {
            return objs.get(objs.size() - 1);
        } else return null;

    }

    @Override
    public Obj getObject(String uri, String bucket, int version) {

        List<Obj> objs = jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=? AND uri=? AND version=?", new BeanPropertyRowMapper<Obj>(Obj.class), bucket, uri,version);
        if (objs.size() > 0) {
            return objs.get(objs.size() - 1);
        } else return null;
    }

    @Override
    public List<Obj> objectsFromUser() {
        return jdbcTemplate.query("SELECT * FROM Object WHERE username_owner=?", new BeanPropertyRowMapper<Obj>(Obj.class), (String) session.getAttribute("username"));
    }

    @Override
    public List<Obj> objectsFromBucket(String bucket) {
        return jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=?", new BeanPropertyRowMapper<Obj>(Obj.class), bucket);
    }

    @Override
    public void deleteObject(String bucket, String obj,int version) {

        String username = (String) session.getAttribute("username");
        jdbcTemplate.update("DELETE FROM Object WHERE uri=? AND bucketUri=? AND version=? AND username_owner=?", obj, bucket,version,username);
    }

    @Override
    public List<String> getUri(String bucket, String obj) {

        obj = obj + "%";

        String sql = "SELECT * FROM Object WHERE bucketUri=? AND uri LIKE ?";

        List<Obj> objs = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Obj>(Obj.class), bucket, obj);

        List<String> uris = new ArrayList<>();

        for (Obj o : objs) {
            uris.add(o.getUri());
        }
        return uris;
    }

    @Override
    public List<Obj> getAllVersions(String bucket,String obj) {
        return jdbcTemplate.query("SELECT * FROM Object WHERE bucketUri=? AND uri=?",new BeanPropertyRowMapper<Obj>(Obj.class),bucket,obj);
    }

    @Override
    public void deleteObject(String bucket, String uri) {
        jdbcTemplate.update("DELETE FROM object WHERE bucketUri=? AND uri=? AND username_owner=?",bucket,uri,session.getAttribute("username"));
    }

    @Override
    public void deleteObject(int id) {
        jdbcTemplate.update("DELETE FROM object WHERE id=? AND username_owner=?",id,session.getAttribute("username"));
    }
}
