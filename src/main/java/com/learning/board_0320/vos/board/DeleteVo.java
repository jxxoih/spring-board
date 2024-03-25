package com.learning.board_0320.vos.board;

import com.learning.board_0320.enums.DeleteResult;

public class DeleteVo {
    private final int articleId;

    private DeleteResult result;

    public DeleteVo(int articleId) {
        this.articleId = articleId;
    }

    public int getArticleId() {
        return articleId;
    }

    public DeleteResult getResult() {
        return result;
    }

    public void setResult(DeleteResult result) {
        this.result = result;
    }
}
