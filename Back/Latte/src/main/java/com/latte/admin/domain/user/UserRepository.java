package com.latte.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select u from User u where u.uid=:uid")
    Optional<User>  findByUid(@Param("uid") String uid);

    @Query("select u from User u where u.uname=:uname and u.uemail=:uemail")
    Optional<User>  findByNameEmail(@Param("uname") String uname, @Param("uemail") String uemail);

    @Query("select u from User u where u.uemail=:uemail")
    Optional<User>  findByEmail(@Param("uemail") String uemail);

    @Modifying
    @Query("UPDATE User u set u.upass = :upass where u.uid = :uid")
    void updatePass(@Param("uid") String uid, @Param("upass") String upass);

}
