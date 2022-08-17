package com.studyCafe.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

    @RequestMapping("members/login")
    public String login(){
        return "members/login";
    }

    @GetMapping("/members/join")
    public String join(){
        return "/members/createMemberForm";
    }


}
