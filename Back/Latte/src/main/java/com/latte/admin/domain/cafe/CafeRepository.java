package com.latte.admin.domain.cafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe,Long> {

    // 승인 카페 리스트(대기, 승인, 거절) -> 관리자페이지
    @Query("select c from Cafe c where c.cstatus=:cstatus")
    List<Cafe> cafeStatus(@Param("cstatus") int cstatus);

    // 손님 카페 리스트 -> 손님페이지: 운영중인것 부터 보여주기
    @Query("select c from Cafe c order by c.coperation desc")
    List<Cafe> findAllByDesc();

    @Query("select c from Cafe c where c.ccid=:ccid")
    Cafe findByCcid(@Param("ccid") Long ccid);

    // cstatus변경
    @Modifying
    @Query("update Cafe c set c.cstatus=:cstatus where c.ccid=:ccid")
    void setStatus(@Param("ccid") Long ccid,@Param("cstatus") int cstatus);

    // coperation변경
    @Modifying
    @Query("update Cafe c set c.coperation=:coperation where c.ccid=:ccid")
    void setOperation(@Param("ccid") Long ccid,@Param("coperation") int coperation);
}