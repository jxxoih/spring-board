package com.learning.board_0320.services;

import com.learning.board_0320.dtos.ArticleDto;
import com.learning.board_0320.mappers.IBoardMapper;
import com.learning.board_0320.vos.ListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        vo.setArticles(articles);
    }

}
