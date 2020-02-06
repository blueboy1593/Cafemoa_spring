package com.latte.admin.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.cafereview.ccid=:ccid")
    List<Review> findByCcid(@Param("ccid") Long ccid);
}
