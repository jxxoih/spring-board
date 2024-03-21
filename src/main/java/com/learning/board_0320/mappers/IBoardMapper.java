package com.learning.board_0320.mappers;

import com.learning.board_0320.dtos.ArticleDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface IBoardMapper {
    ArrayList<ArticleDto> selectArticles(
            @Param("offset") int offset,
            @Param("limit") int limit
    );
}
