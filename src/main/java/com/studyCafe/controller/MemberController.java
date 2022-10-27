package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
import com.studyCafe.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class MemberController {

    private MemberService memberService;
    private SeatService seatService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/login")
    public String loginForm(HttpServletRequest request) {
        return "members/login";
    }

    @GetMapping("members/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    @PostMapping(value = "members/login")
    public String login(Member member, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("MemberId", member.getId());
        return "redirect:/";
    }

    @GetMapping("members/memberCheck")
    @ResponseBody
    public String memberCheck(Member member, HttpServletRequest request){
        // id, pw 확인
        // F인 경우 index T인 경우 login
        boolean result = memberService.validateMember(member);

        if(result){
            return "false";
        }

        HttpSession session = request.getSession();
        session.setAttribute("MemberId", member.getId());
        return "true";
    }

    @GetMapping("members/new")
    public String createForm(){
        return "/members/join";
    }

    @PostMapping(value = "members/new")
    public String create(Member member){
        memberService.join(member);
        return "/members/login";
    }
    /*
    @GetMapping(value = "members/idCheck")
    @ResponseBody
    public Optional<Member> idCheck(Member member){
        System.out.println(member.getId());
        return memberService.validateDuplicateMember(member);
    }
    */

}
