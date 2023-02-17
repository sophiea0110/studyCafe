package com.studyCafe.controller;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import com.studyCafe.service.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class SeatController {

    private SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping(value = "seat/seatForm")
    public String seatForm(){
        return "/seat/seatForm";
    }


    @GetMapping(value = "seat/AllfindSeat")
    @ResponseBody
    public List<Seat> AllfindSeat(){
        return seatService.AllcurrentSeat();
    }

    @GetMapping(value = "seat/findSeat")
    @ResponseBody
    public Optional<Seat> FindSeat(@RequestParam String MemberId){
        return seatService.findSeat(MemberId);
    }

    @PostMapping(value = "seat/select")
    @ResponseBody
    public Optional<Seat> SeatChoice(Seat seat){
        return seatService.saveSeat(seat);
    }


    @PostMapping(value = "seat/recover")
    @ResponseBody
    public Optional<Seat> SeatRecover(Seat seat){
        return seatService.returnSeat(seat);
    }
}
