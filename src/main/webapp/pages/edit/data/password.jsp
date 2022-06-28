<%--
  Created by IntelliJ IDEA.
  User: Trani
  Date: 2022/6/21
  Time: 上午 05:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改密碼</title>

    <!-- 靜態包含 base標籤 引入Jquery -->
    <%@ include file="../../common/head.jsp"%>

    <style>
        #wrapper {
            width: 1200px;

            text-align: center;
        }
    </style>
</head>
<body>

<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../../common/title.jsp"%>

<div id="wrapper">
    <form action="userServlet" method="post">
        <!-- 隱藏域 決定調用後端哪個方法 -->
        <input type="hidden" name="action" value="editPassword">

        舊密碼<input type="text" name="oldPassword"> <br/>
        新密碼<input type="password" name="newPassword"> <br/>
        確認新密碼<input type="password" name="checkNewPassword"> <br/>
        <input type="submit" value="送出"> <br/>
    </form>

</div>


</body>
</html>
