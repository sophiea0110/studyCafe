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

		Member member = new Member();

		member.setId("aaa");
		member.setTiket("fourHour");

		LocalDateTime localStart = LocalDateTime.now();
		Timestamp stampStart = Timestamp.valueOf(localStart);

		LocalDateTime localEnd = localStart.plusMinutes(member.getRemainingTime());
		Timestamp stampEnd = Timestamp.valueOf(localEnd);

		Seat seat = new Seat();
		seat.setId(member.getId());
		seat.setSeatNumber(1);
		seat.setStartTime(stampStart);
		seat.setEndTime(stampEnd);
		System.out.println(seat.toString());

		seatService.saveSeat(seat);

	}

	@Test
	void 좌석반납() throws ParseException {
		//좌석 조회
		String MemberId = "ddd";

		Seat seat = seatService.findSeat(MemberId);
		Member member =	memberService.validateDuplicateMember(MemberId);

		//사용시간 연산
		Timestamp stampStart = seat.getStartTime();
		LocalDateTime localStart = stampStart.toLocalDateTime();
		LocalDateTime localEnd = LocalDateTime.now();

		Duration duration = Duration.between(localStart, localEnd);
		long betweenResult = duration.getSeconds() / 60;
		long remainingTime = member.getRemainingTime() - betweenResult;

		System.out.println("현재시간 : " + LocalDateTime.now());
		System.out.println("duration.getSeconds : " + duration.getSeconds() / 60 + "분");
		System.out.println("remainingTime : " + remainingTime + " 분");

		// 사용자 잔여 시간 업데이트
		memberService.remainingUpdate(MemberId, remainingTime);
		// 사용자가 반납할 시트 정보 제거
		seatService.returnSeat(MemberId);
	}

	@Test
	void 좌석조회(){
		String MemberId = "aaa";
		Seat seat = seatService.findSeat(MemberId);

		System.out.println(seat.toString());

	}
	@Test
	void 회원가입(){
		Member member = new Member();

		member.setId("aaa");
		member.setPw("123");
		member.setEmail("aaa@aaa");
		member.setTiket("fourHour");

		System.out.println(member.toString());

		memberService.join(member);
	}
}
