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
    public int searchSeat(String MemberId) {
        Object obj = em.createQuery("select seatNumber from Member m where m.id = :id")
                .setParameter("id", MemberId)
                .getSingleResult()
                .toString();
        int result = Integer.parseInt((String)obj);
        System.out.println("사용중인 좌석 = " + result);
        return result;
    }
}
