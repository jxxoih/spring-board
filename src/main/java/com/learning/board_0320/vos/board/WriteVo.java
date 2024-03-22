package com.learning.board_0320.vos.board;

import com.learning.board_0320.enums.WriteResult;

public class WriteVo {
    private String board_title;
    private String board_content;
    private String board_writer;

    private WriteResult result;

    public WriteVo(String board_title, String board_content, String board_writer) {
        this.board_title = board_title;
        this.board_content = board_content;
        this.board_writer = board_writer;
    }

    public String getBoard_title() {
        return board_title;
    }

    public void setBoard_title(String board_title) {
        this.board_title = board_title;
    }

    public String getBoard_content() {
        return board_content;
    }

    public void setBoard_content(String board_content) {
        this.board_content = board_content;
    }

    public String getBoard_writer() {
        return board_writer;
    }

    public void setBoard_writer(String board_writer) {
        this.board_writer = board_writer;
    }

    public WriteResult getResult() {
        return result;
    }

    public void setResult(WriteResult result) {
        this.result = result;
    }
}
