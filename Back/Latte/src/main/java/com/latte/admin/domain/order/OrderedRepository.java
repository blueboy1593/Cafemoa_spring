package com.latte.admin.domain.order;

import com.latte.admin.domain.cafe.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {

    @Query("select o from Ordered o where o.ooid=:ooid")
    Ordered findByOoid(@Param("ooid") Long ooid);

    // ostatus변경
    @Modifying
    @Query("update Ordered o set o.ostatus=:ostatus")
    void setStatus(@Param("ostatus") int ostatus);
}
