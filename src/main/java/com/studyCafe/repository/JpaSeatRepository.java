package com.studyCafe.repository;

import com.studyCafe.domain.Seat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class JpaSeatRepository implements SeatRepository {

    private final EntityManager em;

    public JpaSeatRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Seat save(Seat seat) {
        em.persist(seat);
        return seat;
    }
}
