package com.project.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.models.User;

import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select * from users  u join roles r on u.rolds= r.role_id", nativeQuery = true)
    List<User> get();

    //    @Modifying
//    @Transactional
//    @Query(value = "update users set username=:username, name=:name, password=:password, " +
//            "dob=:dob,email=:email,point=:point,rolds=:role_id,updatetime=:timeupdate where userid=:userid", nativeQuery = true)
//    void customUpdateUser(@Param("username") String username, @Param("name") String name,
//                          @Param("password") String password, @Param("dob") LocalDate dob,
//                          @Param("email") String email,@Param("point") int point,
//                          @Param("role") Long role_id,LocalDateTime timeupdate, @Param("userid") Long userid);
    List<User> findByUsernameContaining(String s);

    @Modifying
    @Transactional
    @Query(value = "select * from Users where role_id=:role_id", nativeQuery = true)
    List<User> filterUserByRold(@Param("role_id") Long i);
}


