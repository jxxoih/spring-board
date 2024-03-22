<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>list</title>
</head>

<body>

<table>
    <thead>
        <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일</th>
            <th>조회수</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="article" items="${vo.articles}">
            <tr onclick="window.location.href='/board/read/${article.board_id}?is=0&p=${vo.page}'">
                <td>${article.board_id}</td>
                <td>${article.board_title}</td>
                <td>${article.board_writer}</td>
                <td>${article.formattedTimestamp}</td>
                <td>${article.view_count}</td>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
    <tr class="page">
        <td colspan="5" style="text-align: center">
            <c:if test="${vo.page > 1}">
                <span>
                    <a href="/board/list/1" target="_self">&lt;&lt;</a>
                </span>
            </c:if>
            <c:forEach var="i" begin="${vo.leftPage}" end="${vo.rightPage}" step="1">
                <c:if test="${i == vo.page}">
                    <span>
                        <strong><a>${i}</a></strong>
                    </span>
                </c:if>
                <c:if test="${i != vo.page}">
                    <span>
                        <a href="/board/list/${i}" target="_self">${i}</a>
                    </span>
                </c:if>
            </c:forEach>
            <c:if test="${vo.page < vo.maxPage}">
                <span>
                    <a href="/board/list/${vo.maxPage}" target="_self">&gt;&gt;</a>
                </span>
            </c:if>
        </td>
    </tr>
    </tfoot>
</table>
<div>
    <a href="/board/write">글작성</a>
</div>
</body>
</html>