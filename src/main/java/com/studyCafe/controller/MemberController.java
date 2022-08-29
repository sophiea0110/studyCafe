package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {

    private MemberService memberService;

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
        // id, pw 확인
        // F인 경우 index T인 경우 login
        boolean result = memberService.validateMember(member);

        if(result){
            return "/members/login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("MemberId", member.getId());
        return "redirect:/";
    }

    @GetMapping("members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping(value = "members/new")
    public String create(Member member){
        memberService.join(member);
        return "/members/login";
    }


}
