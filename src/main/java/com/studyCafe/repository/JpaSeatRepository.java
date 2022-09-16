package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;

    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int updateBySeatNumber(Member member) {
        return em.createQuery("UPDATE Member m SET m.seatNumber = :seatNumber where m.id = :id")
                .setParameter("seatNumber", member.getSeatNumber())
                .setParameter("id", member.getId())
                .executeUpdate();
    }

    @Override
    public int initBySeatNumber(Member member) {
        return em.createQuery("UPDATE Member m SET m.seatNumber = 0 where m.id = :id")
                .setParameter("id", member.getId())
                .executeUpdate();
    }

    @Override
    public List<Member> AllSeat() {
        List<Member> result = em.createQuery("select m from Member m", Member.class)
                .getResultList();
    /*
        result.stream().forEach(e ->  {
            System.out.println(e.getSeatNumber() + "번의 사용자는 " + e.getId());
        });
    */
        return result;
    }

    @Override
    public int searchSeat(String MemberId) {
        System.out.println(MemberId);
        Object obj = em.createQuery("select seatNumber from Member m where m.id = :id")
                .setParameter("id", MemberId)
                .getSingleResult()
                .toString();
        int result = Integer.parseInt((String)obj);
        System.out.println(result);
        return result;
    }
}
