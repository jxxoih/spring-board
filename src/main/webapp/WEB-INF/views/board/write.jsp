<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>list</title>
</head>

<body>

<form method="post">
    <div class="title">
        <label>
            <span hidden>제목</span>
            <input autofocus maxlength="100" name="board_title" type="text" placeholder="제목을 입력하세요." value="${vo.board_title}">
        </label>
    </div>
    <div class="content">
        <label>
            <span hidden>내용</span>
            <textarea maxlength="10000" name="board_content" placeholder="내용을 입력하세요">${vo.board_content}</textarea>
        </label>
    </div>

    <div class="action">
        <input type="reset" value="초기화">
        <input type="submit" value="글작성">
    </div>
</form>

</body>
</html>