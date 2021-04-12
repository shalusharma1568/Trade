package com.test.Traders.schedular;

import com.test.Traders.entity.Traders;
import com.test.Traders.repository.TradersRepository;
import com.test.Traders.service.TradersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class DateScheduler {
    private static final Logger LOG = LoggerFactory.getLogger(DateScheduler.class);
    private static final String EXPIRED = "Y";

    @Autowired
    private TradersRepository tradersRepository;

    @Scheduled(cron = "0 0 12 * * ?")
    @Transactional
    public void updateFlag() {
        LOG.info("Date scheduler started !");
        tradersRepository.update(LocalDateTime.now(), EXPIRED);
        LOG.info("Records updated successfully !");
    }
}
