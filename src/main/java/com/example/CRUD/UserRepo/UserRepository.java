package com.example.CRUD.UserRepo;

import com.example.CRUD.ApiResponse.ApiResponse;
import com.example.CRUD.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from user_table where id= :id",nativeQuery = true)
    UserEntity updateBy(int id);

    UserEntity findByName(String name);

    UserEntity findByNameAndPassword(String name, String email);

}
