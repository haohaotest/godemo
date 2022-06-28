<%--
  Created by IntelliJ IDEA.
  User: Trani
  Date: 2022/6/20
  Time: 上午 09:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登入</title>
    <!-- 靜態包含 動態獲取base標籤 和引入Jquery -->
    <%@ include file="../common/head.jsp"%>

    <style>
        #wrapper {
            width: 1200px;

            text-align: center;
        }
    </style>
</head>

<body>

<!-- 靜態包含 標題導航欄 -->
<%@ include file="../common/title.jsp"%>

<div id="wrapper">
    <!-- 登入表單 -->
    <form action="userServlet" method="post">
        <input type="hidden" name="action" value="login">
        帳號：<input type="text" name="username" value="${ requestScope.username }"><br/>
        密碼：<input type="password" name="password" value="${ requestScope.password }"><br/>
        <input type="submit" value="登入">
    </form>

    <!-- 錯誤資訊顯示 (若有才顯示 span特點是沒內容就不顯示標籤) -->
    <span>${ requestScope.message }</span>

    <!-- 回首頁功能的a標籤 -->
    <a href="pages/index/index.jsp">回首頁</a>
</div>

</body>
</html>
