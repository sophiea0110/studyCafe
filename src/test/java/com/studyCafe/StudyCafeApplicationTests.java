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

		DateTimeFormatter form = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:MM:SS");

		LocalDateTime localStart = LocalDateTime.now();
		Timestamp stampStart = Timestamp.valueOf(localStart.format(form));

		LocalDateTime localEnd = localStart.plusHours(4);
		Timestamp stampEnd = Timestamp.valueOf(localEnd.format(form));

		System.out.println(stampStart);
		System.out.println(stampEnd);


		Seat seat = new Seat();
		seat.setId("iii");
		seat.setSeatNumber(9);
		seat.setStartTime(stampStart);
		seat.setEndTime(stampEnd);

		seatService.saveSeat(seat);

	}

	@Test
	void 좌석조회(){
		String MemberId = "ccc";
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
