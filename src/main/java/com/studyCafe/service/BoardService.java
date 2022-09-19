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

    public int SearchBoardCount(String searchKind, String searchWord){
        return boardRepository.SearchBoardCount(searchKind, searchWord);
    }

    public List<Board> selectBoard(PagingVO vo){
        return boardRepository.selectBoard(vo);
    }

    public Board DetailBoard(Long no){
        return boardRepository.findByNo(no);
    }

    public int ModifyBoard(Board board) {
        return boardRepository.updataBoard(board);
    }

    public int deleteBoard(Long no) {
        return boardRepository.deleteBoard(no);
    }

    public List<Board> searchBoard(String searchKind, String searchWord, PagingVO vo) {
        return boardRepository.findByWord(searchKind, searchWord, vo);
    }
}
