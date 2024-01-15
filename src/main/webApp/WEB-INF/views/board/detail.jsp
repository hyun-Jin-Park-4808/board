<%@taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">Back</button>
    <c:if test="${boards.user.id == principal.user.id}">
        <a href="/board/${boards.id}/updateForm" class="btn btn-warning">Modify</a>
        <button onclick="index.deleteById(${boards.id})" class="btn btn-danger">Delete</button>
    </c:if>
    <br><br>
    <div>
        Post ID : <span id="id"><i>${boards.id} </i></span>
        Post Writer : <span><i>${boards.user.username} </i></span>
    </div>
    <br>
    <div class="form-group">
        <h3>${boards.title}</h3>
    </div>

    <div class="form-group">
        <span class="label label-info">${boards.category}</span>
    </div>
    <hr>
    <div class="form-group">
        <div>${boards.content}</div>
    </div>
    <div class="card">
        <form>
            <input type="hidden" id="userId" value="${principal.user.id}">
            <input type="hidden" id="boardId" value="${boards.id}">
            <div class="card-body">
                <textarea id="reply-content" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="button" id="btn-reply-save" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
    <br>
    <div class="card">
        <div class="card-header">Reply List</div>
        <ul id="reply-box" class="list-group">
            <c:forEach var="reply" items="${boards.replys}">
                <li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
                    <div>${reply.content}</div>
                    <div class="d-flex">
                        <div class="font-italic">Writer : ${reply.user.username} </div><br>
                        <c:if test="${principal.user.username eq reply.user.username}">
                            <button onclick="index.deleteReply(${boards.id}, ${reply.id})" class="badge">Remove</button>
                        </c:if>
                    </div>
                </li>
            </c:forEach>
        </ul>
    </div>
    <hr>

</div>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>