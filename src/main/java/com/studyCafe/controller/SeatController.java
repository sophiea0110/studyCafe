package com.studyCafe.controller;

import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SeatController {

    private MemberService memberService;

    public SeatController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping(value = "seat/select")
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

    @GetMapping(value = "seat/findSeat")
    @ResponseBody
    public  String FindSeat(@RequestParam String MemberId){
        System.out.println("FindSeat MemberId = " + MemberId);
        return "findSeat 정상적으로 실행되었습니다.";
    }
}
