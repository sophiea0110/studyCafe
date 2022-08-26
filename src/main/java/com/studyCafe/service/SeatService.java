package com.studyCafe.service;

import com.studyCafe.domain.Seat;
import com.studyCafe.repository.SeatRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class SeatService {

    private final SeatRepository seatRepository;


    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat join(Seat seat){
        return seatRepository.save(seat);
    }
}
