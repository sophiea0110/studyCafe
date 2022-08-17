package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
}
