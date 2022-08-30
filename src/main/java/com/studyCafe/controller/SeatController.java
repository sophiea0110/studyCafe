package com.studyCafe.controller;

import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeatController {

    private MemberService memberService;

    public SeatController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "seat/choice")
    @ResponseBody
    public int SeatChoice(Member member){

        //System.out.println("SeatChoice id : " + member.getId());
        //System.out.println("SeatChoice seatNumber : " + member.getSeatNumber());
        return memberService.assignSeat(member);
    }
    @PostMapping(value = "seat/recover")
    @ResponseBody
    public int SeatRecover(Member member){

        //System.out.println("SeatRecover id : " + member.getId());
        //System.out.println("SeatRecover seatNumber : " + member.getSeatNumber());
        return memberService.returnSeat(member);
    }
}
