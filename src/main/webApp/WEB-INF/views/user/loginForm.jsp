<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp"%>

<!-- key: username, value: input box에 들어간 내용으로 백엔드에 요청-->
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">User Name</label>
            <input type="username" name="username" class="form-control" placeholder="Enter username" id="username">
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <c:if test="${not empty error}">
            <span>
                <p id="valid" class="alert alert-danger">
                        ${exception}
                </p>
            </span>
        </c:if>
        <button id="btn-login" class="btn btn-primary">Sign In</button>
    </form>
</div>
<%@ include file="../layout/footer.jsp" %>