package com.studyCafe.controller;

import com.studyCafe.domain.Seat;
import com.studyCafe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
