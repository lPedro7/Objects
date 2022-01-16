package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Bucket;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BucketDAO extends CrudRepository<Bucket,String> {

    @Modifying
    @Query("insert into bucket values(:uri,:usernameOwner)")
    void createBucket(@Param("uri") String uri,@Param("usernameOwner") String usernameOwner);

    Bucket getBucketByUriAndUsernameOwner( String uri,String usernameOwner);

    @Modifying
    @Query("DELETE FROM bucket WHERE uri=:uri")
    void removeBucketByUri(@Param("uri") String uri);

    List<Bucket> getBucketsByUsernameOwner(String s);
}
