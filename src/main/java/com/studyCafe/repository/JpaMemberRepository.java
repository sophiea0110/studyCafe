package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class JpaMemberRepository implements MemberRepository {

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
    public Member findById(String id) {
        Member result = em.createQuery("select m from Member m where m.id = :id", Member.class)
                .setParameter("id", id)
                .getSingleResult();
        System.out.println("findById result = " + result);
        return result;
    }

    @Override
    public Optional<Member> findByMember(Member member) {
        List<Member> result = em.createQuery("select m from Member m where m.id = :id and m.pw = :pw", Member.class)
                .setParameter("id", member.getId())
                .setParameter("pw", member.getPw())
                .getResultList();
        return result.stream().findAny();
    }

    public void updateByRemaining(Member member){
        em.createQuery("update Member m set m.remainingTime = :remainingTime where m.id = :MemberId")
                .setParameter("remainingTime", member.getRemainingTime())
                .setParameter("MemberId", member.getId())
                .executeUpdate();
    }

    /*
    @Override
    public Optional<Member> findByRemaingTime(Member member) {
        Member result = em.createQuery("select m from Member m where m.id = :id", Member.class)
        return
    }

     */
}
