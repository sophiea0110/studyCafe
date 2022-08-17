package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("members/login")
    public String login(){
        return "members/login";
    }

    @GetMapping("members/join")
    public String createForm(){
        return "/members/createMemberForm";
    }

    @PostMapping(value = "members/join")
    public String create(Member member){
        System.out.println(member.getId());
        System.out.println(member.getPw());
        return "redirect:/";
    }

}
