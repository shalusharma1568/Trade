package com.test.Traders;

import com.test.Traders.entity.Traders;
import com.test.Traders.service.TradersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class TradersApplicationTests {

	@Autowired
	private TradersService tradersService;

	@Test
	void validateMaturityDateLess() {
		assertThrows(RuntimeException.class, ()->{DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			Traders traders = new Traders();
			traders.setTradeId("T1");
			traders.setVersion(1);
			traders.setCounterPartyId("CP-1");
			traders.setBookId("B1");
			traders.setMaturityDate(LocalDateTime.parse("2020-05-20T00:00:00.000Z", formatter));
			traders.setCreatedDate(LocalDateTime.now());
			traders.setExpired("N");
			tradersService.create(traders);} );
	}

	@Test
	void createTradeAndUpdateIfSame() {
		DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
		Traders t1 = new Traders();
		t1.setTradeId("T2");
		t1.setVersion(1);
		t1.setCounterPartyId("CP-1");
		t1.setBookId("B1");
		t1.setMaturityDate(LocalDateTime.parse("2021-05-20T00:00:00.000Z", formatter));
		t1.setCreatedDate(LocalDateTime.now());
		t1.setExpired("N");
		tradersService.create(t1);

		Traders t2 = new Traders();
		t2.setTradeId("T2");
		t2.setVersion(1);
		t2.setCounterPartyId("CP-1");
		t2.setBookId("B1");
		t2.setMaturityDate(LocalDateTime.parse("2021-05-20T00:00:00.000Z", formatter));
		t2.setCreatedDate(LocalDateTime.now());
		t2.setExpired("N");
		tradersService.create(t2);
	}

	@Test
	void lowerVersionCheck() {
		assertThrows(RuntimeException.class, ()->{
			DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
			Traders t1 = new Traders();
			t1.setTradeId("T1");
			t1.setVersion(1);
			t1.setCounterPartyId("CP-1");
			t1.setBookId("B1");
			t1.setMaturityDate(LocalDateTime.parse("2021-05-20T00:00:00.000Z", formatter));
			t1.setCreatedDate(LocalDateTime.now());
			t1.setExpired("N");
			tradersService.create(t1);

			Traders t2 = new Traders();
			t2.setTradeId("T1");
			t2.setVersion(2);
			t2.setCounterPartyId("CP-1");
			t2.setBookId("B1");
			t2.setMaturityDate(LocalDateTime.parse("2021-05-20T00:00:00.000Z", formatter));
			t2.setCreatedDate(LocalDateTime.now());
			t2.setExpired("N");
			tradersService.create(t2);

			Traders t3 = new Traders();
			t3.setTradeId("T1");
			t3.setVersion(1);
			t3.setCounterPartyId("CP-1");
			t3.setBookId("B1");
			t3.setMaturityDate(LocalDateTime.parse("2021-05-20T00:00:00.000Z", formatter));
			t3.setCreatedDate(LocalDateTime.now());
			t3.setExpired("N");
			tradersService.create(t3);} );
	}

}
