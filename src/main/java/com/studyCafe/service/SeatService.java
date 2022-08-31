package com.studyCafe.service;

import com.studyCafe.domain.Member;
import com.studyCafe.repository.MemberRepository;
import com.studyCafe.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public int findSeat(String MemberId) { return seatRepository.searchSeat(MemberId); }

    public int assignSeat(Member member){
        return seatRepository.updateBySeatNumber(member);
    }

    public int returnSeat(Member member){
        return seatRepository.initBySeatNumber(member);
    }
}
