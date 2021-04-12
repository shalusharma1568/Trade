package com.test.Traders.repository;

import com.test.Traders.entity.Traders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TradersRepository extends JpaRepository<Traders, String> {

    @Query("SELECT t FROM Traders t WHERE t.TradeId =:TradeId AND t.version =:version")
    public Traders findByTradeAndVersion(@Param("TradeId") String TradeId, @Param("version") int version );

    @Query("SELECT t FROM Traders t WHERE t.TradeId =:TradeId order by t.version desc")
    public List<Traders> findTrades(@Param("TradeId") String TradeId);

    @Modifying
    @Query("UPDATE Traders t SET t.expired =:expired WHERE t.maturityDate < :todayDate")
    public void update(@Param("todayDate") LocalDateTime todayDate, @Param("expired") String expired);
}
