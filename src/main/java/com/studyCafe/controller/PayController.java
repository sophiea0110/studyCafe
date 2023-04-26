package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import com.studyCafe.service.MemberService;
import com.studyCafe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class PayController {
    private MemberService memberService;
    private SeatService seatService;


    @Autowired
    public PayController(MemberService memberService, SeatService seatService) {

        this.memberService = memberService;
        this.seatService = seatService;
    }

    @GetMapping("payment/payForm")
    public String payForm(){
        return "payment/payForm";
    }

    @GetMapping("payment/addTime")
    public String addTime(@RequestParam("MemberId") String MemberId,
                          @RequestParam("minute") long minute) {

        Member member = memberService.findMember(MemberId);
        member.setRemainingTime(member.getRemainingTime() + minute);

        System.out.println("회원아이디 : " + member.getId());
        System.out.println("시간(원래시간 + 추가시간) : " + member.getRemainingTime());

        memberService.remainingUpdate(member);
        //Optional<Seat> seat = seatService.findSeat(member.getId());

        //System.out.println(seat.getClass());


        //seatService.addTimeAfterEndTimeUpdate(member);
        return "redirect:/";
    }
}
