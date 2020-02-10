package com.latte.admin.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NaverRepository extends JpaRepository<Naver,Long> {

    @Query("select u from User u where u.uemail=:uemail")
    List<Naver> findByEmail(@Param("uemail") String uemail);
}
