package com.studyCafe.controller;

import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
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

    @GetMapping(value = "seat/AllfindSeat")
    @ResponseBody
    public List<Member> AllfindSeat(){
        return seatService.AllcurrentSeat();
    }

    @GetMapping(value = "seat/findSeat")
    @ResponseBody
    public int FindSeat(@RequestParam String MemberId){
        //System.out.println("FindSeat MemberId = " + MemberId);
        return seatService.findSeat(MemberId);
    }

    @PostMapping(value = "seat/select")
    @ResponseBody
    public int SeatChoice(Member member){
        //System.out.println("SeatChoice id : " + member.getId());
        //System.out.println("SeatChoice seatNumber : " + member.getSeatNumber());
        return seatService.assignSeat(member);
    }
    @PostMapping(value = "seat/recover")
    @ResponseBody
    public int SeatRecover(Member member){
        //System.out.println("SeatRecover id : " + member.getId());
        //System.out.println("SeatRecover seatNumber : " + member.getSeatNumber());
        return seatService.returnSeat(member);
    }
}
