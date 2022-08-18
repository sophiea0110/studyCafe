package com.studyCafe.repository;

import com.studyCafe.domain.Member;

import java.util.Optional;

public interface MemberRepository {
    public Member save(Member member);
    public Optional<Member> findByName(String name);
}
