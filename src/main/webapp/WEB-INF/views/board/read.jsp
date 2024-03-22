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
        <th>작성자</th>
        <td>${vo.board_writer}</td>
        <th>작성일시</th>
        <td>${vo.formattedTimestamp}</td>
        <th>조회수</th>
        <td>${vo.view_count}</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td colspan="6">
            ${vo.board_content}
        </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td colspan="6">
            <a href="/board/list/${param.p}">목록</a>
        </td>
    </tr>
    </tfoot>
</table>

</body>
</html>