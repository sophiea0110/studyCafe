package com.studyCafe.service;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import com.studyCafe.repository.MemberRepository;
import com.studyCafe.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public class SeatService {

    private SeatRepository seatRepository;
    private MemberRepository memberRepository;


    public SeatService(SeatRepository seatRepository, MemberRepository memberRepository) {
        this.seatRepository = seatRepository;
        this.memberRepository = memberRepository;
    }
    public Optional<Seat> saveSeat(Seat seat) {

        Member member = memberRepository.findById(seat.getId());

        LocalDateTime localStart = LocalDateTime.now();
        Timestamp stampStart = Timestamp.valueOf(localStart);

        LocalDateTime localEnd = localStart.plusMinutes(member.getRemainingTime());
        Timestamp stampEnd = Timestamp.valueOf(localEnd);

        seat.setStartTime(stampStart);
        seat.setEndTime(stampEnd);

        return seatRepository.save(seat);
    }

    public List<Seat> AllcurrentSeat() { return seatRepository.AllSeat(); }

    public Optional<Seat> findSeat(String MemberId) { return seatRepository.searchSeat(MemberId); }

    public Optional<Seat> returnSeat(Seat seat){

        Member member = memberRepository.findById(seat.getId());
        //Optional<Seat> seat = seatService.findSeat(seat.getId());

        Timestamp stampStart = seat.getStartTime();

        LocalDateTime localStart = stampStart.toLocalDateTime();
        LocalDateTime localEnd = LocalDateTime.now();

        Duration duration = Duration.between(localStart, localEnd);

        long betweenResult = duration.getSeconds() / 60;
        long remainingTime = member.getRemainingTime() - betweenResult;

        member.setRemainingTime(remainingTime);

        // 사용자 잔여 시간 업데이트
        memberRepository.updateByRemaining(member);
        // 사용자가 반납할 시트 정보 제거
        return seatRepository.returnSeat(seat);
    }

    public void addTimeAfterEndTimeUpdate(Member member){
        seatRepository.EndTimeUpdate(member);
    }
}
