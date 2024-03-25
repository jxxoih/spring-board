package com.learning.board_0320.vos.board;

import com.learning.board_0320.enums.EditResult;

public class EditVo {
    private final String board_title;
    private final String board_content;

    private int articleId;
    private EditResult result;

    public EditVo(String board_title, String board_content) {
        this.board_title = board_title;
        this.board_content = board_content;
    }


    public String getBoard_title() {
        return board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public EditResult getResult() {
        return result;
    }

    public void setResult(EditResult result) {
        this.result = result;
    }
}
