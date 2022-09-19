package com.studyCafe.repository;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.Member;
import com.studyCafe.domain.PagingVO;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.sound.midi.SysexMessage;
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
    public int SearchBoardCount(String searchKind, String searchWord) {
        String quere = ""
                + "select count(m) "
                + "from Board m "
                + "where " + searchKind + " like " + "'%" + searchWord + "%'";

        Object obj = em.createQuery(quere)
                .getSingleResult()
                .toString();
        int result = Integer.parseInt((String) obj);
        System.out.println("SearchCount" + result);
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

    @Override
    public Board findByNo(Long no) {
        Board result = (Board) em.createQuery("select m from Board m where no=:no", Board.class)
                .setParameter("no", no)
                .getSingleResult();

        System.out.println(result.getNo());
        System.out.println(result.getId());
        System.out.println(result.getTitle());
        System.out.println(result.getContents());

        return result;
    }

    @Override
    public int updataBoard(Board board) {
        int result =  em.createQuery("Update Board m " +
                        "set title = :title, contents = :contents" +
                        " where no = :no")
                .setParameter("title", board.getTitle())
                .setParameter("contents", board.getContents())
                .setParameter("no", board.getNo())
                .executeUpdate();
        System.out.println("업데이트 :" + result);
        return result;
    }

    @Override
    public int deleteBoard(Long no) {
        int result = em.createQuery("delete from Board m where no = :no")
                .setParameter("no", no)
                .executeUpdate();
        return result;
    }

    @Override
    public List<Board> findByWord(String searchKind, String searchWord, PagingVO vo) {
        /*
        String quere = ""
                + "select m "
                + "from Board m "
                + "where " + searchKind + " like " + "'%" + searchWord + "%'";
        */
        String quere = ""
                +  "SELECT RN, no, id, title, contents "
                +  "FROM("
                +  "SELECT ROWNUM RN, no, id, title, contents "
                +  " FROM("
                +          "SELECT no, id, title, contents "
                +          "FROM BOARD m "
                +          "WHERE " + searchKind + " LIKE " + "'%" + searchWord + "%'"
                +          "ORDER BY no DESC "
                +    ")"
                + ")"
                + "WHERE RN BETWEEN :start AND :end";
        List<Board> result = em.createNativeQuery(quere, Board.class)
                .setParameter("start", vo.getStart())
                .setParameter("end", vo.getEnd())
                .getResultList();
        System.out.println(searchKind+" 에서 " + searchWord +" 를 검색한 결과 " + result.size()+" 개를 찾았습니다!");
        return result;
    }

}
