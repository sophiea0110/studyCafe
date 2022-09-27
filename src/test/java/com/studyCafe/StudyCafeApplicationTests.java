package com.studyCafe;

import com.studyCafe.domain.Seat;
import com.studyCafe.repository.JpaSeatRepository;
import com.studyCafe.repository.SeatRepository;
import com.studyCafe.service.SeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@SpringBootTest
class StudyCafeApplicationTests {

	@Autowired SeatService seatService;
	@Autowired SeatRepository seatRepository;

	@Test
	void SeatInsert(){

		Seat seat = new Seat();

		seat.setId("song");
		seat.setSeatNumber(10);

		String aDate = "2022-01-01";
		String bDate = "2023-01-01";
		String cDate = "0001-01-01";

		SimpleDateFormat a = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat b = new SimpleDateFormat("yyyymmdd");
		SimpleDateFormat c = new SimpleDateFormat("yyyymmdd");

		Date x = null;
		Date y = null;
		Date z = null;

	try {
		x = a.parse(aDate);
		y = b.parse(bDate);
		z = a.parse(cDate);
	} catch (ParseException e) {
		throw new RuntimeException(e);
	}

	seat.setStartTime(x);
	seat.setEndTime(y);
	seat.setRemainingTime(z);

	System.out.println(seat.getId());
	System.out.println(seat.getSeatNumber());
	System.out.println(seat.getStartTime());
	System.out.println(seat.getEndTime());
	System.out.println(seat.getRemainingTime());

	seatService.saveSeat(seat);

	}
}
