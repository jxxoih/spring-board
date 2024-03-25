<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Edit</title>
</head>

<body>

<form method="post">
    <div class="title">
        <input autofocus maxlength="100" name="board_title" type="text" placeholder="제목을 입력하세요." value="${vo.board_title}">
    </div>
    <div class="content">
        <textarea maxlength="1000" name="board_content">${vo.board_content}</textarea>
    </div>

    <div class="action">
        <input type="submit" value="글수정">
    </div>
</form>
<div>
    <a href="/board/read/${vo.board_id}">돌아가기</a>
</div>

</body>
</html>