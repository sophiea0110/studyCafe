package com.studyCafe.service;

import com.studyCafe.domain.Member;
import com.studyCafe.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member join(Member member){
        return memberRepository.save(member);
    }

    public Member findMember(String MemberId) {
       return memberRepository.findById(MemberId);
    }

    public boolean validateMember(Member member){
        Optional<Member> list = memberRepository.findByMember(member);
        System.out.println("ValidateMamber MemberId = " + member.getId());
        System.out.println(list);
        System.out.println(list.isEmpty());
        return list.isEmpty();
    }

    public void remainingUpdate(Member member){
        memberRepository.updateByRemaining(member);
    }

    public Member RemainingTimeCheck(Member member) {
        return memberRepository.findById(member.getId());
    }

}
