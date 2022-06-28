<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <!-- 靜態包含 base標籤 引入Jquery -->
    <%@ include file="../common/head.jsp"%>

    <style>
        #wrapper {
            width: 1200px;
        }

        /* 用戶數據展示 */
        #userInfoDiv {

        }

        a {
            text-decoration: none;
        }
    </style>
</head>
<body>

<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../common/title.jsp"%>

    <div id="wrapper">
        <div id="userInfoDiv">
            用戶名：${ sessionScope.user.username }<br/>
            用戶密碼：${ sessionScope.user.password }　<a href="pages/edit/data/password.jsp">修改</a><br/>
            練習題數：${ sessionScope.user.exerTotalQuery }<br/>
            答對題數：${ sessionScope.user.exerBingoQuery }<br/>
            練習時長：${ sessionScope.user.exerTime }<br/>
            用戶創建時間：${ sessionScope.user.createAccountTime }<br/>
        </div>
    </div>

</body>
</html>
