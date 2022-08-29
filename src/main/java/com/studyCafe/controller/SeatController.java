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
    public void SeatChoice(Member member){

        System.out.println("choice id : " + member.getId());
        System.out.println("choice seatNumber : " + member.getSeatNumber());
        memberService.seatSelect(member.getSeatNumber(), member.getId());

    }
    @PostMapping(value = "seat/recover")
    @ResponseBody
    public void SeatRecover(){

    }
}
