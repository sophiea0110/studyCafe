package com.studyCafe.service;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import com.studyCafe.repository.MemberRepository;
import com.studyCafe.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class SeatService {

    private SeatRepository seatRepository;


    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }
    public void saveSeat(Seat seat) {  seatRepository.save(seat); }

    public List<Member> AllcurrentSeat() { return seatRepository.AllSeat(); }

    public Seat findSeat(String MemberId) { return seatRepository.searchSeat(MemberId); }

    /*
    public int assignSeat(Member member){
        return seatRepository.updateBySeatNumber(member);
    }
    */

    public void returnSeat(String MemberId){
        seatRepository.returnSeat(MemberId);
    }
}
