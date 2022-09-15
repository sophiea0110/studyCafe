package com.studyCafe.repository;

import com.studyCafe.domain.Board;

import java.util.List;

public interface BoardRepository {

    public Board save(Board board);
    public List<Board> allBoard();


}
