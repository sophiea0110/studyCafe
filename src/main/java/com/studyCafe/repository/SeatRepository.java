package com.studyCafe.repository;

import com.studyCafe.domain.Member;

public interface SeatRepository {

    public int searchSeat(String MemberId);
    public int updateBySeatNumber(Member member);
    public int initBySeatNumber(Member member);
}
