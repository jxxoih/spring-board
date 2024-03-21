package com.learning.board_0320.vos;

import com.learning.board_0320.dtos.ArticleDto;

import java.util.ArrayList;

public class ListVo {
     private final int page;
     private ArrayList<ArticleDto> articles;

     public ListVo(int page) {
          this.page = page;
     }

     public ArrayList<ArticleDto> getArticles() {
          return articles;
     }

     public void setArticles(ArrayList<ArticleDto> articles) {
          this.articles = articles;
     }

     public int getPage() {
          return page;
     }
}
