package com.studyCafe.controller;

import com.studyCafe.domain.seat.Seat;
import com.studyCafe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SeatController {

    private SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("seats/new")
    public Seat seats(Seat seat){
        System.out.println("응답 완료!");
        System.out.println(seat.getMemberId());
        System.out.println(seat.getSeatNumber());
        seatService.join(seat);
        return seat;
    }

}
