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
	@Autowired MemberService memberService;

	@Test
	void 좌석선택(){

		String memberId = "aaa";
		Member member = memberService.findMember(memberId);

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
	void 좌석반납(){
		//좌석 조회
		String MemberId = "aaa";
		Member member =	memberService.findMember(MemberId);

		Seat seat = seatService.findSeat(member.getId());

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

		member.setRemainingTime(remainingTime);

		// 사용자 잔여 시간 업데이트
		memberService.remainingUpdate(member);
		// 사용자가 반납할 시트 정보 제거
		seatService.returnSeat(member.getId());
	}

	@Test
	void 좌석조회(){

		String MemberId = "aaa";
		Member member = memberService.findMember(MemberId);
		Seat seat = seatService.findSeat(member.getId());

		System.out.println(seat.toString());

	}

	@Test
	void 시간추가(){
		// 로그인한 아이디로 DB에 해당 아이디를 객체로 가져온다
		String memberId = "aaa";
		Member member = memberService.findMember(memberId);

		// 객체의 remainingTime에 시간 추가
		long remainingTime = 4;
		member.setRemainingTime(remainingTime);

		// 해당 튜플에 업데이트
		memberService.remainingUpdate(member);
	}

	@Test
	void 회원가입(){
		Member member = new Member();

		member.setId("aaa");
		member.setPw("123");
		member.setEmail("aaa@aaa");

		System.out.println(member.toString());

		memberService.join(member);
	}
}
