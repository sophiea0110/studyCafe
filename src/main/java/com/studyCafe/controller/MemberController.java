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

    @GetMapping("members/login")
    public String loginForm() {
        return "members/login";
    }

    @PostMapping(value = "members/login")
    public String login(Member member, Model model){
        // id, pw 확인
        // T인 경우 index F인 경우 login
        boolean result = memberService.validateMember(member);

        if(result)  return "/members/login";

        model.addAttribute("id", member.getId());
        return "index";
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
