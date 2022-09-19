package com.studyCafe.controller;


import com.studyCafe.domain.Board;
import com.studyCafe.domain.Member;
import com.studyCafe.domain.PagingVO;
import com.studyCafe.service.BoardService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    private BoardService boardService;

    @GetMapping(value = "/board/boardList")
    public String boardList(PagingVO vo, Model model, @RequestParam(value="nowPage", required=false) String nowPage) {


        int total = boardService.countBoard();
        if (nowPage == null) {
            nowPage = "1";
        }

        vo = new PagingVO(total, Integer.parseInt(nowPage));

        model.addAttribute("paging", vo);
        model.addAttribute("boardList", boardService.selectBoard(vo));
        return "board/boardList";
    }

    @GetMapping(value = "/board/boardWrite")
    public String boardWriteForm(){
        return "board/boardWrite";
    }

    @PostMapping(value = "/board/boardWrite")
    public String boardWrite(Board board){
        System.out.println("작성자 : "+ board.getId());
        boardService.writeBoard(board);
        return "redirect:/board/boardList";
    }

    @GetMapping(value = "/board/boardDetail")
    public String boardDetail(@RequestParam(value="no", required = false) Long no, Model model, Board board){
        board = boardService.DetailBoard(no);
        model.addAttribute("boardDetail", board);
        return "board/boardDetail";
    }

    @GetMapping(value = "/board/boardModify")
    public String boardModifyForm(@RequestParam(value="no", required = false) Long no, Model model, Board board){
        board = boardService.DetailBoard(no);
        model.addAttribute("boardDetail", board);
        return "board/boardModify";
    }

    @PostMapping(value = "/board/boardModify")
    public String boardModify(Board board){
        String source = "?no=   " + board.getNo();
        boardService.ModifyBoard(board);
        return "redirect:/board/boardDetail" + source;
    }

    @GetMapping(value = "/board/boardDelete")
    public String boardDelete(@RequestParam(value="no", required = false) Long no){
        boardService.deleteBoard(no);
        return "redirect:/board/boardList";
    }

    @GetMapping(value = "/board/boardSearch")
    //@ResponseBody
    public String boardSearch(@RequestParam String searchKind, @RequestParam String searchWord,
                              @RequestParam(value="nowPage", required=false) String nowPage, Model model){

        System.out.println(searchKind);
        System.out.println(searchWord);

        if (nowPage == null) {
            nowPage = "1";
        }

        int total = boardService.SearchBoardCount(searchKind, searchWord);
        System.out.println("갯수" + total);
        PagingVO vo = new PagingVO(total, Integer.parseInt(nowPage));
        List<Board> board =  boardService.searchBoard(searchKind, searchWord, vo);

        System.out.println("시작" + vo.getStart());
        System.out.println("끝" + vo.getEnd());
        System.out.println("시작페이지" + vo.getStartPage());
        System.out.println("끝 페이지" + vo.getEndPage());


        model.addAttribute("searchKind", searchKind);
        model.addAttribute("searchWord", searchWord);
        model.addAttribute("paging", vo);
        model.addAttribute("boardList", board);

        return "board/boardSearch";
    }

}
