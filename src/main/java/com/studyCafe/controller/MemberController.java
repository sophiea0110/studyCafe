package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import com.studyCafe.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("members/login")
    public String login() {
        return "members/login";
    }

    @GetMapping("members/new")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping(value = "members/new")
    public String create(Member member){
        memberService.join(member);
        return "redirect:/members/login";
    }

}
