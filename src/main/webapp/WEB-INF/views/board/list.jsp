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
            <tr>
                <td>${article.board_id}</td>
                <td>${article.board_title}</td>
                <td>${article.board_writer}</td>
                <td>${article.formattedTimestamp}</td>
                <td>${article.view_count}</td>
            </tr>
        </c:forEach>
    </tbody>
    <tfoot>
        <td colspan="5">
            <span>
                <a>&lt;&lt;</a>
            </span>
            <span>
                <strong>
                    <a>1</a>
                </strong>
            </span>
            <span>
                <a>&gt;&gt;</a>
            </span>
        </td>
    </tfoot>
</table>

</body>
</html>