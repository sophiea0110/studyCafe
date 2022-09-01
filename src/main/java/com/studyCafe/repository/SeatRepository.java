package com.studyCafe.repository;

import com.studyCafe.domain.Member;

import java.util.List;

public interface SeatRepository {

    public int searchSeat(String MemberId);
    public int updateBySeatNumber(Member member);
    public int initBySeatNumber(Member member);
    public List<Member> AllSeat();
}
