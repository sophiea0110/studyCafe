package com.studyCafe.service;

import com.studyCafe.domain.seat.Seat;
import com.studyCafe.repository.seatrepository.SeatRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional("transactionManagerSeat")
public class SeatService {

    private final SeatRepository seatRepository;


    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat join(Seat seat){
        return seatRepository.save(seat);
    }
}
