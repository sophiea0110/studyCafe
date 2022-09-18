package com.studyCafe.repository;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.Member;
import com.studyCafe.domain.PagingVO;
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
    public int allBoardCount() {
        Object obj = em.createQuery("select count(m) from Board m")
                .getSingleResult()
                .toString();
        int result = Integer.parseInt((String) obj);
        //System.out.println("총 게시판 수 : " + result);
        return result;

    }

    @Override
    public List<Board> selectBoard(PagingVO vo) {
        String quere  = ""
            +  "SELECT RN, no, id, title, contents "
            +  "FROM("
            +  "SELECT ROWNUM RN, no, id, title, contents "
            +  " FROM("
            +          "SELECT no, id, title, contents "
            +          "FROM BOARD m "
            +          "ORDER BY no DESC "
            +    ")"
            + ")"
            + "WHERE RN BETWEEN :start AND :end";
        //System.out.println(quere);
        List<Board> result = em.createNativeQuery(quere, Board.class)
                .setParameter("start", vo.getStart())
                .setParameter("end",vo.getEnd())
                .getResultList();
        return result;
    }
}
