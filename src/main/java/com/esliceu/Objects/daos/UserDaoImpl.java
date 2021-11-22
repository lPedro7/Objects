package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM User",new BeanPropertyRowMapper<User>());
    }

    @Override
    public User getUser(String username) {

        List<User> users = jdbcTemplate.query("SELECT * FROM User WHERE username=?",
                new BeanPropertyRowMapper<User>(User.class),
                username);

        if (users.size() == 0) return null;

        return users.get(0);

    }

    @Override
    public void createUser(String username,
                           String password,
                           String firstName,
                           String lastName,
                           Date birthDate,
                           String email) {
        jdbcTemplate.update("INSERT INTO User(username,password,name,surname,birthDate,email) VALUES(?,?,?,?,?,?)",username,password,firstName,lastName,birthDate,email);
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update("DELETE FROM User WHERE username=?",username);
    }
}
