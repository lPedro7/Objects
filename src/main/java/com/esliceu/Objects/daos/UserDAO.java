package com.esliceu.Objects.daos;
import com.esliceu.Objects.model.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDAO extends CrudRepository<User,String> {

    User getUserByUsername(String username);

    @Override
    <S extends User> S save(S entity);

    @Modifying
    @Query("DELETE FROM user WHERE username=:s")
    void deleteUserByUsername(@Param("s") String s);

    @Modifying
    @Query("UPDATE user SET password=:password,name=:name,surname=:surname,birthDate=:birthDate,email=:email")
    void updateUser(@Param("password") String password,
                    @Param("name") String name,
                    @Param("surname") String surname,
                    @Param("birthDate") Date birthDate,
                    @Param("email") String email);
}
