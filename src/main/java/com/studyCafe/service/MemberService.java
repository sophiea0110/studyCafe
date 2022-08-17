package com.studyCafe.service;

import com.studyCafe.domain.Member;
import com.studyCafe.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member join(Member member){
        return memberRepository.save(member);
    }
}
