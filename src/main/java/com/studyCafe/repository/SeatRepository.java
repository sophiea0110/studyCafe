package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {

    public Optional<Seat> searchSeat(String MemberId);
    //public int updateBySeatNumber(Member member);
    public int initBySeatNumber(Member member);
    public List<Seat> AllSeat();

    public Optional<Seat> save(Seat seat);

    public Optional<Seat> returnSeat(Seat seat);
    public void EndTimeUpdate(Member member);

    public void seatEndTimeCheck();
}
