<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()"> <!-- 인증된 사용자라면 -->
    <sec:authentication property="principal" var="principal"/>  <!-- principal이라는 이름의 데이터를 principal이라는 변수에 담는다.-->
</sec:authorize>
<!DOCTYPE html>
<html lang="en">
<head> <!-- 헤더에는 브라우저의 탭에 출력될 이름이 들어간다. -->
    <title>Board project</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<!-- 사용할 외부 자바스크립트 파일들 추가 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> <!-- 페이지의 내부 요소 쉽게 다룰 수 있게 해줌 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script> <!-- ui를 쉽게 꾸밀 수 있는 프레임워크 -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet"> <!-- 게시글 작성할 때 사용 -->
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

<body>
<!-- 상단 메뉴바 구성 -->
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="/">Home</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <!-- jstl 문법, switch 문에 해당 -->
        <!-- c:when = case에 해당 -->
        <!--c:otherwise = default에 해당, 로그인 하면 아래가 실행됨. -->
        <c:choose>
            <c:when test="${empty principal}">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/loginForm">Sign In</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/auth/joinForm">Sign Up</a>
                    </li>
                </ul>
            </c:when>
            <c:otherwise>
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="/board/saveForm">Write</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/user/updateForm">Info</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/logout">Sign Out</a>
                    </li>
                </ul>
            </c:otherwise>
        </c:choose>
    </div>
</nav>
</body>
</html>