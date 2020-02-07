package com.latte.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select u from User u where u.uid=:uid")
    User findByuid(@Param("uid") String uid);

    // 회원가입시 이메일 중복 확인
    @Query("select u from User u where u.uemail=:uemail")
    List<User>  findByEmail(@Param("uemail") String uemail);

    // 회원가입시 아이디 중복 확인
    @Query("select u from User u where u.uid=:uid")
    List<User> checkByUid(@Param("uid") String uid);

    // 아이디 찾기
    @Query("select u from User u where u.uname=:uname and u.uemail=:uemail")
    List<User> findByNameEmail(@Param("uname") String uname, @Param("uemail") String uemail);


    @Modifying
    @Query("UPDATE User u set u.upass = :upass where u.uid = :uid")
    void updatePass(@Param("uid") String uid, @Param("upass") String upass);

    @Query("select u from User u where u.uid=:uid")
    User selectByUid(@Param("uid") String uid);
}
