package com.studyCafe.repository;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.PagingVO;

import java.util.List;

public interface BoardRepository {

    public Board save(Board board);

    public int allBoardCount();

    public int SearchBoardCount(String searchKind, String searchWord);

    public List<Board> selectBoard(PagingVO vo);

    public Board findByNo(Long no);

    public int updataBoard(Board board);

    public int deleteBoard(Long no);

    List<Board> findByWord(String searchKind, String search, PagingVO vo);
}
