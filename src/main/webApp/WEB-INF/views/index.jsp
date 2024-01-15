<%@ taglib prefix="comment" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="layout/header.jsp" %>

<div class="container">
    <%-- 게시글 리스트 불러오는 부분 시작 --%>
    <c:forEach var="boards" items="${boards.content}">
    <div class="card m-2">
        <div class="card-body">
            <h4 class="card-title">${boards.title}</h4>
            <a href="/board/${boards.id}" class="btn btn-primary">Detail</a>
        </div>
    </div>
    </c:forEach>
    <%-- 게시글 리스트 불러오는 부분 끝 --%>
    <%-- 페이지 네이션 동작 시작 --%>
    <ul class="pagination justify-content-center">
        <c:choose>
        <c:when test="${boards.first}">
            <li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:when>
            <c:otherwise>
            <li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
            </c:otherwise>
            </c:choose>
        <c:choose>
            <c:when test="${boards.last}">
            <li class=" page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:when>
            <c:otherwise>
            <li class=" page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
            </c:otherwise>
            </c:choose>
    </ul>
    <%-- 페이지 네이션 동작 끝 --%>
</div>
<%@ include file="layout/footer.jsp" %>