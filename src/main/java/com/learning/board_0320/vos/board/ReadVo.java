package com.learning.board_0320.vos.board;

import com.learning.board_0320.dtos.ArticleDto;

public class ReadVo {
    private final int articleId;

    private ArticleDto article;

    public ReadVo(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }
}
