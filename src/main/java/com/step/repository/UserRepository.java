package com.step.repository;


import com.step.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

/**
 * Created by Виктор on 30.11.2015.
 */
public interface UserRepository  extends CrudRepository<User,Long>
{
    @Override
    User save(User s);

    @Override
    User findOne(Long id);

    @Override
    boolean exists(Long aLong);

    @Override
    Collection<User> findAll();

    @Override
    long count();

    @Override
    void delete(Long id);

    User findByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE name like %:name% and id <>:id",nativeQuery = true)
    Collection<User> findByFilter(@Param(value = "name")String name,@Param(value = "id") long id);
}
