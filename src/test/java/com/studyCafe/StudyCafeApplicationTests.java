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
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
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

		Seat seat = new Seat();
		seat.setId("ccc");
		seat.setSeatNumber(2);
		seat.setStartTime(stampStart);

		seatService.saveSeat(seat);

	}

	@Test
	void 좌석반납() throws ParseException {
		//좌석 조회
		String MemberId = "aaa";
		Seat seat = seatService.findSeat(MemberId);

		//사용시간 연산
		Timestamp stampStart = seat.getStartTime();
		LocalDateTime localStart = stampStart.toLocalDateTime();
		LocalDateTime localEnd = LocalDateTime.now();
		Duration duration = Duration.between(localStart, localEnd);
		Long remainingTime = Math.abs( (duration.getSeconds() / 60) - 4);

		//System.out.println(duration.getSeconds());
		System.out.println(duration.getSeconds() / 60 + "분");

		//사용자 사용시간 DB 업데이트
		memberService.remainingUpdate(MemberId, remainingTime);


	}

	@Test
	void 좌석조회(){
		String MemberId = "ccc";
		Seat seat = seatService.findSeat(MemberId);

		System.out.println(seat.toString());

	}
	@Test
	void 회원가입(){
		Member member = new Member();

		member.setId("ccc");
		member.setPw("123");
		member.setEmail("ccc@ccc");
		member.setTiket("fourHour");

		System.out.println(member.toString());

		memberService.join(member);
	}
}
