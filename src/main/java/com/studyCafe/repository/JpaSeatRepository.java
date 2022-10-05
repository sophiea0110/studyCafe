package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaSeatRepository implements SeatRepository{

    private final EntityManager em;

    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    /*
    @Override
    public int updateBySeatNumber(Member member) {
        return em.createQuery("UPDATE Member m SET m.seatNumber = :seatNumber where m.id = :id")
                .setParameter("seatNumber", member.getSeatNumber())
                .setParameter("id", member.getId())
                .executeUpdate();
    }
    */
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
    public void save(Seat seat) {
        em.persist(seat);
    }

    @Override
    public void returnSeat(String MemberId) {
        Seat seat = em.find(Seat.class, MemberId);
        em.remove(seat);
    }

    @Override
    public Seat searchSeat(String MemberId) {
        System.out.println(MemberId);
        Seat seat = em.createQuery("select m from Seat m where m.id = :id", Seat.class)
                .setParameter("id", MemberId)
                .getSingleResult();
        return seat;
    }
}
