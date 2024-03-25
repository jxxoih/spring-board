package com.learning.board_0320.services;

import com.learning.board_0320.dtos.ArticleDto;
import com.learning.board_0320.enums.DeleteResult;
import com.learning.board_0320.enums.EditResult;
import com.learning.board_0320.enums.WriteResult;
import com.learning.board_0320.mappers.IBoardMapper;
import com.learning.board_0320.vos.board.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class BoardService {

    private static final int ARTICLES_PER_PAGE = 10;
    private static final int PAGE_RANGE = 2;

    private final IBoardMapper boardMapper;

    @Autowired
    public BoardService(IBoardMapper boardMapper) {
        this.boardMapper = boardMapper;
    }

    public void getArticles(ListVo vo) {


        ArrayList<ArticleDto> articles = boardMapper.selectArticles(
                BoardService.ARTICLES_PER_PAGE * (vo.getPage() - 1),
                BoardService.ARTICLES_PER_PAGE
        );

        int articleCount = boardMapper.selectArticleCount();
        // 현재 게시판의 모든 글들의 수를 불러온다
        int maxPage = articleCount / BoardService.ARTICLES_PER_PAGE + (articleCount % BoardService.ARTICLES_PER_PAGE == 0 ? 0 : 1);
        // 마지막 page : 현재 게시판의 모든 글들의 수 / ARTICLES_PER_PAGE(페이지당 있을 글의 수)
        //         + (모든 글 수 % 10 했을 때 나머지가 0 이면 0이고 아니면 1)   result : 게시판 모든 글들의 수 / 10 + (0 or 1)
        int leftPage = Math.max(1, vo.getPage() - BoardService.PAGE_RANGE);
        int rightPage = Math.min(maxPage, vo.getPage() + BoardService.PAGE_RANGE);
        vo.setMaxPage(maxPage);
        vo.setLeftPage(leftPage);
        vo.setRightPage(rightPage);

        vo.setArticles(articles);
    }


    public void writeArticle(WriteVo vo) {
        String board_writer = "jihok9991@gmail.com";

        if (board_writer.toString().isEmpty() || board_writer.toString().equals("")) {
            vo.setResult(WriteResult.NOT_AUTHORIZED);
            return;
        }

        boardMapper.insertArticle(
                vo.getBoard_title(),
                vo.getBoard_content(),
                board_writer
        );

        vo.setResult(WriteResult.OKAY);
    }

    public void getArticle(ReadVo vo) {

//        TODO::view counting 처리 필요


        ArticleDto articleDto = boardMapper.selectArticle(vo.getArticleId());
        vo.setArticle(articleDto);
    }

    @Transactional
    public void deleteArticle(DeleteVo vo) {
        boardMapper.deleteArticle(vo.getArticleId());
        boardMapper.insertDeleteLog(vo.getArticleId());

        vo.setResult(DeleteResult.OKAY);
    }

    public void editArticle(EditVo vo) {
        String board_writer = "jihok9991@gmail.com";

        if(board_writer.isEmpty()
                || board_writer.equals("")
                || !board_writer.equals(boardMapper.selectBoardWriter(vo.getArticleId()))) {
            vo.setResult(EditResult.NOT_AUTHORIZED);
            return;
        }

        boardMapper.updateArticle(
                vo.getArticleId(),
                vo.getBoard_title(),
                vo.getBoard_content(),
                board_writer
        );

        vo.setResult(EditResult.OKAY);
    }

    public void boardViewCount(int aid) {
        boardMapper.updateBoardViewCount(aid);
    }
}
