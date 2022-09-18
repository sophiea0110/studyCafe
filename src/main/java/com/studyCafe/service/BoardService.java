package com.studyCafe.service;

import com.studyCafe.domain.Board;
import com.studyCafe.domain.PagingVO;
import com.studyCafe.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    /*
    public List<Board> AllfindBoard(){
        return boardRepository.findAll();
    }
    */
    public Board writeBoard(Board board){
        return boardRepository.save(board);
    }


    public int countBoard(){
        return boardRepository.allBoardCount();
    }

    public List<Board> selectBoard(PagingVO vo){
        return boardRepository.selectBoard(vo);
    }

    public Board DetailBoard(Long no){
        return boardRepository.findByNo(no);
    }
}
