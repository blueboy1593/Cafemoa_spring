package com.latte.admin.domain.order;

import com.latte.admin.domain.cafe.Cafe;
import com.latte.admin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderedRepository extends JpaRepository<Ordered, Long> {

    // ooid 기준으로 보여주기
    @Query("select o from Ordered o where o.ooid=:ooid")
    Ordered findByOoid(@Param("ooid") Long ooid);

    // uid기준으로 보여주기
    @Query("select o from Ordered o where o.orderuser.uuid=uuid")
    List<Ordered> findByUuid(@Param("uuid") Long uuid);

    // ccid기준으로 보여주기
    @Query("select o from Ordered o where o.ccid=ccid")
    List<Ordered> findByCcid(@Param("ccid") Long ccid);

    // ostatus변경
    @Modifying
    @Query("update Ordered o set o.ostatus=:ostatus")
    void setStatus(@Param("ostatus") int ostatus);
}
