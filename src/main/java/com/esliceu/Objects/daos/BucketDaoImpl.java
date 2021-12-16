package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Bucket;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

@Repository
public class BucketDaoImpl implements BucketDAO {

    @Autowired
    HttpSession session;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void createBucket(String uri) {
        String username = (String) session.getAttribute("username");
        uri = Utils.unaccent(uri);
        jdbcTemplate.update("INSERT INTO Bucket VALUES(?,?)",uri,username);
    }

    @Override
    public Bucket getBucket(String uri) {
        String username = (String) session.getAttribute("username");
        List<Bucket> buckets = jdbcTemplate.query("SELECT * FROM Bucket WHERE LOWER(uri)=? AND LOWER(username_owner)=?", new BeanPropertyRowMapper<Bucket>(Bucket.class), uri.toLowerCase(), username.toLowerCase());
        if (buckets.size() > 0) {
            return buckets.get(0);
        }
        return null;
    }

    @Override
    public void deleteBucket(String uri) {
        String username = (String) session.getAttribute("username");
        jdbcTemplate.update("DELETE FROM Bucket WHERE uri=? AND username_owner=?",uri,username);
    }

    @Override
    public List<Bucket> getForUser(String s) {
        String username = (String) session.getAttribute("username");
        return jdbcTemplate.query("SELECT * FROM Bucket WHERE LOWER(username_owner)=?",new BeanPropertyRowMapper<Bucket>(Bucket.class),username.toLowerCase());
    }
}
