package com.latte.admin.domain.cafe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe,Long> {
    @Query("select c from Cafe c order by c.createdDate desc")
    List<Cafe> findAllDesc();

    @Query("select c from Cafe c where c.ccid=:ccid")
    Cafe findByCcid(@Param("ccid") Long ccid);

}
