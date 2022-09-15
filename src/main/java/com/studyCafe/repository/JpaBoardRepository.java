package com.studyCafe.repository;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
public class JpaBoardRepository implements  BoardRepository{

    private  final EntityManager em;

    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    }

    public Board save(Board board){
        em.persist(board);
        return board;
    }

    @Override
    public List<Board> allBoard() {
        List<Board> result = em.createQuery("select m from Board m", Board.class)
                .getResultList();
        return result;
    }
}
