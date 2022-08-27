package com.studyCafe.repository.seatrepository;

import com.studyCafe.domain.seat.Seat;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
