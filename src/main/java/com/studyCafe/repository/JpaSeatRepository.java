package com.studyCafe.repository;

import com.studyCafe.domain.Member;
import com.studyCafe.domain.Seat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

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
    public List<Seat> AllSeat() {
        List<Seat> result = em.createQuery("select m from Seat m", Seat.class)
                .getResultList();
    /*
        result.stream().forEach(e ->  {
            System.out.println(e.getSeatNumber() + "번의 사용자는 " + e.getId());
        });
    */
        return result;
    }

    @Override
    public Optional<Seat> save(Seat seat) {
        em.persist(seat);
        return Optional.ofNullable(seat);

    }


    @Override
    public Optional<Seat> returnSeat(Seat seat) {
        Seat findSeat = em.find(Seat.class, seat.getId());
        em.remove(findSeat);
        /*
        List<Seat> resultList = em.createQuery("select m from Seat m where m.id = :id", Seat.class)
                .setParameter("id", seat.getId())
                .getResultList();
        System.out.println(resultList);
        System.out.println("seat value = " + seat);
        */
        return Optional.ofNullable(findSeat);


    }

    @Override
    public void EndTimeUpdate(Member member) {
        int seat = em.createQuery("UPDATE Seat m SET m.endTime = :endTime where m.id = :id")
                .setParameter("endTime", member.getRemainingTime())
                .setParameter("id", member.getId())
                .executeUpdate();
    }

    @Override
    public Optional<Seat> searchSeat(String MemberId) {
        System.out.println("searchSeat id = " + MemberId);
        List<Seat> seat = null;
        try {
            seat = em.createQuery("select m from Seat m where m.id = :id", Seat.class)
                    .setParameter("id", MemberId)
                    .getResultList();
            System.out.println("seat value = " + seat);

        } catch (Exception e) {
            System.out.println("error msg = " + e);
        }
        return seat.stream().findAny();
    }
}
