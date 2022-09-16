package com.studyCafe.repository;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.PagingVO;

import java.util.List;

public interface BoardRepository {

    public Board save(Board board);
    public int allBoardCount();

    public List<Board> selectBoard(PagingVO vo);

}
