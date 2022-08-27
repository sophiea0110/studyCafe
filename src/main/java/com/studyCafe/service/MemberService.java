package com.studyCafe.service;

import com.studyCafe.domain.member.Member;
import com.studyCafe.repository.memberrepository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional("transactionManager")
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public Member join(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public boolean validateMember(Member member){
        Optional<Member> list = memberRepository.findByMember(member);
        System.out.println("ValidateMamber MemberId = " + member.getId());
        return list.isEmpty();
    }
}
