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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@SpringBootTest
class StudyCafeApplicationTests {

	@Autowired SeatService seatService;
	@Autowired SeatRepository seatRepository;
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	@Test
	void 좌석선택(){

		Seat seat = new Seat();
		LocalDateTime now = LocalDateTime.now();

		SimpleDateFormat f = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");

		Date f1 = null;
		Date f2;

		try {
			f1 = f.parse(now.toString());
		}catch (IllegalStateException | ParseException c){

		}
		System.out.println(f1);

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
