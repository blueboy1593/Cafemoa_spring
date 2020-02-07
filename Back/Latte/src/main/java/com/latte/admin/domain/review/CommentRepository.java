package com.latte.admin.domain.review;

import com.latte.admin.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
//
//    // cafe정보찾기 -> 여기에서 사장님 uid찾기위해서!
//    @Query("select c from Cafe c where c.ccid=:ccid")
//    List<Cafe> findByCcid(@Param("ccid") Long ccid);

}
