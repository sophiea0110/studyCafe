package com.studyCafe;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import com.studyCafe.repository.JpaSeatRepository;
import com.studyCafe.repository.MemberRepository;
import com.studyCafe.repository.SeatRepository;
import com.studyCafe.service.MemberService;
import com.studyCafe.service.SeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@SpringBootTest
class StudyCafeApplicationTests {

	@Autowired SeatService seatService;
	@Autowired SeatRepository seatRepository;
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void 좌석선택() throws ParseException {

		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		Seat seat = new Seat();

		seat.setId("aaa");
		seat.setSeatNumber(1);

		Timestamp nowTime = new Timestamp(System.currentTimeMillis());
		Timestamp start = Timestamp.valueOf(s.format(nowTime));

		LocalDateTime setTime
				= LocalDateTime.of(2022, 9, 30, 21, 0, 0);

		Timestamp t1 = Timestamp.valueOf(setTime);
		Timestamp end = Timestamp.valueOf(s.format(t1));

		System.out.println(start);
		System.out.println(end);

		/*
		String startStr = start.format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
		);

		String endStr = end.format(
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
		);

		Date startDate = s.parse(startStr);
		Date endDate = s.parse(endStr);

		System.out.println(startDate);
		System.out.println(endDate);
		*/




		seat.setId("aaa");
		seat.setSeatNumber(1);
		seat.setStartTime(start);
		seat.setEndTime(end);

		seatService.saveSeat(seat);

	}

	@Test
	void 좌석조회(){
		String MemberId = "aaa";
		List<Seat> seat = seatService.findSeat(MemberId);

		seat.stream().forEach( s -> {
			System.out.println(s.getId());
			System.out.println(s.getSeatNumber());
			System.out.println(s.getStartTime());
			System.out.println(s.getEndTime());
		});

	}
	@Test
	void 회원가입(){
		Member member = new Member();

		member.setId("aaa");
		member.setPw("123");
		member.setEmail("aaa@aaa");
		member.setTiket("null");

		memberService.join(member);

	}

}
