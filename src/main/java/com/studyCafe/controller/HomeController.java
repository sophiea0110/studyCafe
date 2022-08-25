package com.studyCafe.controller;


import com.studyCafe.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);

        if(session != null) {
            String MemberId = (String) session.getAttribute("MemberId");
            //model.addAttribute("MemberId", MemberId);
            System.out.println(MemberId + "님 반갑습니다.");

        }
        return "/index";
    }

}
