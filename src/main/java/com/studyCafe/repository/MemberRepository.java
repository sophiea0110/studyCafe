package com.studyCafe.repository;

import com.studyCafe.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findById(String name);
    public Optional<Member> findByMember(Member member);
    public int updateBySeatNumber(Member member);
    public int initBySeatNumber(Member member);
}
