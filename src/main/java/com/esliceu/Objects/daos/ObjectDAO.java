package com.esliceu.Objects.daos;

import com.esliceu.Objects.model.Obj;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface ObjectDAO extends CrudRepository<Obj,String>{


    @Override
    <S extends Obj> S save(S entity);

    Obj getObjById(int id);
    Obj getObjByUriAndBucketUri( String uri,String bucket);
    List<Obj> getObjsByBucketUriAndUri(String bucket,String obj);
    Obj getObjByBucketUriAndUri(String bucket,String obj);
    List<Obj> getObjsByUsernameOwner(String username);
    List<Obj> getObjsByBucketUri(String bucket);

    @Modifying
    @Query("DELETE FROM object WHERE bucketUri=:bucket AND uri = :obj AND version = :version")
    void deleteObjByBucketUriAndUriAndVersion(@Param("bucket") String bucket,@Param("obj") String obj, @Param("version") int version);

    @Modifying
    @Query("DELETE FROM object WHERE bucketUri=:bucket AND uri=:uri")
    void deleteObjsByBucketUriAndUri(@Param("bucket") String bucket,@Param("uri") String uri);

    @Modifying
    @Query("DELETE FROM object WHERE id=:id")
    void deleteObjById(@Param("id") int id);

    @Query("SELECT uri FROM Object o WHERE o.bucketUri=:bucket AND o.uri like :obj ")
    List<String> getUri(@Param("bucket") String bucket,@Param("obj") String obj);

}
