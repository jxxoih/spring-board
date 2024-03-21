package com.learning.board_0320.controllers;

import com.learning.board_0320.services.BoardService;
import com.learning.board_0320.vos.ListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/list")
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping(value = {"/", "/{page}"})
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
}
