package com.learning.board_0320.controllers;

import com.learning.board_0320.enums.WriteResult;
import com.learning.board_0320.services.BoardService;
import com.learning.board_0320.vos.board.ListVo;
import com.learning.board_0320.vos.board.ReadVo;
import com.learning.board_0320.vos.board.WriteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = {"/list", "/list/{page}"})
    public String getList(
            @PathVariable("page")Optional<Integer> optionalPage,
            Model model
            ) {

        // page 데이터가 url에 있으면 그것을 사용하고 아니면 1이다.
        int page = optionalPage.orElse(1);

        ListVo vo = new ListVo(page);

        this.boardService.getArticles(vo);
        model.addAttribute("vo", vo);

        return "board/list";
    }

    @RequestMapping(
            value = "/write",
            method = RequestMethod.GET
    )
    public String getWritePage() {
        return "board/write";
    }

    @RequestMapping(
            value = "/write",
            method = RequestMethod.POST
    )
    public String writePost(
            Model model,
            WriteVo vo
    ) {

        boardService.writeArticle(vo);
        model.addAttribute("vo", vo);
        if(vo.getResult() == WriteResult.OKAY) {
            return "redirect:/board/list";
        } else {
            return "board/write";
        }

    }

    @RequestMapping("/read/{aid}")
    public String getReadPage(
            @PathVariable("aid") int articleId,
            Model model
    ) {

        ReadVo vo = new ReadVo(articleId);
        boardService.getArticle(vo);
        model.addAttribute("vo", vo.getArticle());

        return "board/read";
    }

}
