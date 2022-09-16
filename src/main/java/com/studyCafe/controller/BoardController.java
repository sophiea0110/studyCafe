package com.studyCafe.controller;


import com.studyCafe.domain.Board;
import com.studyCafe.domain.PagingVO;
import com.studyCafe.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    private BoardService boardService;

    /*
    @GetMapping(value = "/board/boardList")
    public String boardForm(Board board, Model model){
        List<Board> boardList = boardService.AllfindBoard();

        System.out.println(boardList);
        model.addAttribute("boardList", boardList);
        return "/board/boardList";
    }
     */

    @GetMapping(value = "/board/boardList")
    public String boardList(PagingVO vo, Model model, @RequestParam(value="nowPage", required=false)String nowPage) {

        int total = boardService.countBoard();
        if (nowPage == null) {
            nowPage = "1";
        }
        vo = new PagingVO(total, Integer.parseInt(nowPage));
        model.addAttribute("paging", vo);
        model.addAttribute("viewAll", boardService.selectBoard(vo));
        return "board/boardList";
    }
}
