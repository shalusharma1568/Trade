package com.test.Traders.service;

import com.test.Traders.entity.Traders;
import com.test.Traders.repository.TradersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TradersService {

    @Autowired
    private TradersRepository tradersRepository;

    public void create(Traders traders){
        if(validate(traders)){
            Traders t1 = tradersRepository.findByTradeAndVersion(traders.getTradeId(), traders.getVersion());
            if(t1 == null){
                tradersRepository.save(traders);
            }else{
                List<Traders> list = tradersRepository.findTrades(traders.getTradeId());
                if(!list.isEmpty()){
                    Traders t2 = list.get(0);
                    if(traders.getVersion() < t2.getVersion() ){
                        throw  new RuntimeException("Lower version is not allowed");
                    }else if (traders.getVersion() == t2.getVersion()){
                        t2.setTradeId(traders.getTradeId());
                        t2.setVersion(traders.getVersion());
                        t2.setCounterPartyId(traders.getCounterPartyId());
                        t2.setBookId(traders.getBookId());
                        t2.setMaturityDate(traders.getMaturityDate());
                        t2.setCreatedDate(traders.getCreatedDate());
                        tradersRepository.save(t2);
                    }
                }
            }
        }else{
            throw new RuntimeException("Maturity date is not validate!");
        }
    }

    public boolean validate(Traders traders){
        if(traders.getMaturityDate().isBefore(LocalDateTime.now()))
            return false;
        return true;
    }
}
