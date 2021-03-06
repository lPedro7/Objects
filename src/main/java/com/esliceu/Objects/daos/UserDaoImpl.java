package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.User;
import com.esliceu.Objects.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private HttpSession session;

    @Override
    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM User",new BeanPropertyRowMapper<User>(User.class));
    }

    @Override
    public User getUser(String username) {

        List<User> users = jdbcTemplate.query("SELECT * FROM User WHERE LOWER(username)=?",
                new BeanPropertyRowMapper<User>(User.class),
                username.toLowerCase());

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
        username= Utils.unaccent(username);
        password= Utils.unaccent(password);
        firstName= Utils.unaccent(firstName);
        lastName= Utils.unaccent(lastName);
        String sql = "INSERT INTO User(username,password,name,surname,birthDate,email) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,username,password,firstName,lastName,birthDate,email);
    }

    @Override
    public void deleteUser(String username) {
        jdbcTemplate.update("DELETE FROM User WHERE username=?",username);
    }

    @Override
    public void updateUser(String password, String firstName, String lastName, Date birthDate, String email) {
        String username = (String) session.getAttribute("username");
        jdbcTemplate.update("UPDATE User SET password=?,name=?,surname=?,birthDate=?,email=? WHERE username=?",password,firstName,lastName,birthDate,email,username);
    }
}
