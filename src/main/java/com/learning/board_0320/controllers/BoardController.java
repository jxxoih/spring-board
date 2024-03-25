package com.learning.board_0320.controllers;

import com.learning.board_0320.enums.DeleteResult;
import com.learning.board_0320.enums.EditResult;
import com.learning.board_0320.enums.WriteResult;
import com.learning.board_0320.services.BoardService;
import com.learning.board_0320.vos.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/read")
    public String getReadPage(
            @RequestParam(value = "aid", required = false) int aid,
            Model model,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        ReadVo vo = new ReadVo(aid);

        /* 조회수 로직 */
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println("쿠키있음");

                if (!cookie.getValue().contains(request.getParameter("aid"))) {
                    System.out.println("쿠키밸류없음");
                    cookie.setValue(cookie.getValue() + "_" + request.getParameter("aid"));
                    System.out.println("cookie::"+cookie.getValue());
                    cookie.setMaxAge(60 * 60 * 2);  /* 쿠키 시간 */
                    response.addCookie(cookie);
                    boardService.boardViewCount(aid);
                    System.out.println("view++");
                }
            }
        } else {
            System.out.println("쿠키없음");
            Cookie newCookie = new Cookie("visit_cookie", request.getParameter("aid"));
            newCookie.setMaxAge(60 * 60 * 2);
            response.addCookie(newCookie);
            boardService.boardViewCount(aid);
        }


        boardService.getArticle(vo);
        model.addAttribute("vo", vo.getArticle());

        return "board/read";
    }

    @RequestMapping("/delete/{aid}")
    public String deleteArticle(
            @PathVariable("aid") int articleId
    ) {
        DeleteVo vo = new DeleteVo(articleId);
        boardService.deleteArticle(vo);

        if(vo.getResult() == DeleteResult.OKAY) {
            return "redirect:/board/list/1";
        } else {
            return "redirect:/board/read/" + articleId;
        }

    }


    @RequestMapping(value = "/edit/{aid}", method = RequestMethod.GET)
    public String getEditArticle(
            @PathVariable("aid") int articleId,
            Model model
    ) {
        ReadVo vo = new ReadVo(articleId);
        boardService.getArticle(vo);
        model.addAttribute("vo", vo.getArticle());

        return "board/edit";
    }

    @RequestMapping(value = "/edit/{aid}", method = RequestMethod.POST)
    public String postEditArticle(
                EditVo vo,
                @PathVariable("aid") int articleId,
                Model model
            ) {

        vo.setArticleId(articleId);
        boardService.editArticle(vo);

        if(vo.getResult() == EditResult.OKAY) {
            return "redirect:/board/read/" + articleId;
        } else {
            model.addAttribute("vo", vo);
            return "board/edit";
        }
    }
}
