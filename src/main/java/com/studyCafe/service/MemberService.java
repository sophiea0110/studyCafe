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

    public Optional<Member> validateDuplicateMember(Member member) {
        return memberRepository.findById(member.getId());
    }

    public boolean validateMember(Member member){
        Optional<Member> list = memberRepository.findByMember(member);
        System.out.println("ValidateMamber MemberId = " + member.getId());
        return list.isEmpty();
    }

    public void remainingUpdate(String MemberId, Long remainingTime){
        memberRepository.updateByRemaining(MemberId, remainingTime);
    }

}
