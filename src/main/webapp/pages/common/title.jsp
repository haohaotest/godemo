<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- 靜態包含base標籤 和引入Jquery -->
<%--    <%@ include file="head.jsp"%>--%>

    <style>
        #titleDiv {
            position: relative;

            margin-bottom: 15px;

            heigth: 50px;

            text-align: center;

            background-color: rgba(0, 0, 0, 0.65);
        }

        #logoImg {
            position: absolute;

            left: 0px;

            width: 100px;
            height: 50px
        }

        #titleH1 {
            display: inline-block;
            line-height: 10px;
        }

        #midDiv {
            display: inline-block;

            height: 50px;
            /*border: 1px black solid;*/
        }

        #navDiv {
            position: absolute;
            right: 10px;
            line-height: 48px;
            display: inline-block;

            height: 48px;
            /*border: 1px black solid;*/
        }

        #navDiv span {
            margin-left: 10px;
        }

        #navDiv span a {
            padding-left: 5px;
            border-left: 1px rgba(101, 97, 97, 0.77) solid;
            text-decoration: none;
        }

    </style>
</head>
<body>
    <div id="titleDiv">
         <%--logo圖片 並可點擊跳轉 --%>
        <a href="index.jsp">
            <img id="logoImg" src="static/image/logo.jpg" title="logo" alt="無法顯示"/>
        </a>


        <%-- 中間標題 --%>
        <div id="midDiv">
            <%--<h1 id="titleH1">學英文！</h1>--%>
        </div>

        <%-- 導航菜單欄 --%>
        <div id="navDiv">
            <%-- 登入狀態顯示的菜單 --%>
            <c:if test="${ !empty sessionScope.user}">
                <span><a href="pages/user/center.jsp">${ sessionScope.user.username }</a></span>
                <span><a href="userServlet?action=logout">登出</a></span>
            </c:if>

            <%-- 沒登入狀態顯示的菜單 --%>
            <c:if test="${ empty sessionScope.user}">
                <span><a href="pages/user/login.jsp">登入</a></span>
                <span><a href="pages/user/regist.jsp">註冊</a></span>
            </c:if>
        </div>
    </div>
</body>
</html>
