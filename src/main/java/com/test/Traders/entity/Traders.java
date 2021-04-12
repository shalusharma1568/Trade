package com.test.Traders.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRADERS")
public class Traders implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TRADE_ID")
    private String TradeId;

    @Column(name = "VERSION")
    private int version;

    @Column(name = "COUNTER_PARTY_ID")
    private String CounterPartyId;

    @Column(name = "BOOK_ID")
    private String bookId;

    @Column(name = "MATURITY_DATE", nullable = true)
    private LocalDateTime maturityDate;

    @Column(name = "CREATED_DATE", nullable = true)
    private LocalDateTime createdDate;

    @Column(name = "EXPIRED", nullable = true)
    private String expired;

    public String getTradeId() {
        return TradeId;
    }

    public void setTradeId(String tradeId) {
        TradeId = tradeId;
    }

    public String getCounterPartyId() {
        return CounterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        CounterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDateTime getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDateTime maturityDate) {
        this.maturityDate = maturityDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
