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
    /*
    @Override
    public List<Board> allBoard() {
        List<Board> result = em.createQuery("select m from Board m", Board.class)
                .getResultList();
        return result;
    }
    */
    @Override
    public int allBoardCount() {
        Object obj = em.createQuery("select count(m) from Board m")
                .getSingleResult()
                .toString();
        int result = Integer.parseInt((String) obj);
        System.out.println("총 게시판 수 : " + result);
        return result;

    }

    @Override
    public List<Board> selectBoard(PagingVO vo) {
        System.out.println(vo.getCntPerPage());
        String quere = "select m " +
                            " from(" +
                                "select rownum as rn, Board m " +
                                "from(" +
                                    "Board m " +
                                    "order by no desc " +
                                ") " +
                            ") " +
                        "where rn between start = :start and end = :end";
        System.out.println(quere);
        List<Board> result = em.createQuery("SELECT * FROM ( SELECT ROWNUM RN, A.* FROM (SELECT * FROM BOARD ORDER BY no DESC ) A) WHERE RN BETWEEN 1 AND 10", Board.class)
                .setParameter("start", 1)
                .setParameter("end",10)
                .getResultList();

        result.stream().forEach( (list) -> {
            System.out.println(list);
        });

        return result;

    }
}
