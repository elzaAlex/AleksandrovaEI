package com.computerAccessoriesStore.repository;

import com.computerAccessoriesStore.models.Act;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ActRepository extends JpaRepository<Act, Long> {
    @Query("SELECT a FROM Act a WHERE a.buyer.id = :id")
    List<Act> findAllByBuyer(@Param("id")Long Id);

    List<Act> findAllBySellerId(Long id);

    @Query("select sum(act.count*p.product_cost)\n" +
            "from Act act\n" +
            "    INNER JOIN Product p on p.id = act.product.id\n" +
            "where act.seller.id = :id group by act.created_at")
    List<Float> findSum(@Param("id")Long id);
}
