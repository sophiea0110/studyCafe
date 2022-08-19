package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Member> findById(String id) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByMember(Member member) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id and m.pw = :pw", Member.class)
                .setParameter("id", member.getId())
                .setParameter("pw", member.getPw())
                .getResultList();
        return result.stream().findAny();
    }
}
