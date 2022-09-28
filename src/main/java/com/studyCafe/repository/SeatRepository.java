package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;

import java.util.List;

public interface SeatRepository {

    public int searchSeat(String MemberId);
    //public int updateBySeatNumber(Member member);
    public int initBySeatNumber(Member member);
    public List<Member> AllSeat();

    public void save(Seat seat);
}
