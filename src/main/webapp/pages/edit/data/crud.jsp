<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>豪豪練英文</title>

    <!-- 靜態包含 base標籤動態獲取值 -->
    <%@ include file="../../common/head.jsp" %>

    <style>
        /* 版心 */
        #wrapper {
            width: 1200px;

            text-align: center;
        }


        body {
            text-align: center;
        }

        a {
            text-decoration: none;
        }

        #okMessage {
            margin-bottom: 17px;
            padding: 3px 10px 3px 10px;

            display: inline-block;

            border: 2px #f5be6c solid;
            border-radius: 15px;

            color: #029755;
        }

        #errorMessage {
            margin-bottom: 17px;
            padding: 3px 10px 3px 10px;

            display: inline-block;

            border: 2px #504949 solid;
            border-radius: 15px;

            color: #c03f31;
        }

        /* 顯示當前操作哪個表 */
        #tableNameSpan {
            border: 1px black solid;
        }

    </style>
</head>
<body>
<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../../common/title.jsp"%>

    <%-- 版心 --%>
    <div id="wrapper">

        <%-- 標題 --%>
        <h1>修改</h1>

        <!-- 選擇操作表 -->
        <a href="wordServlet?action=selectTableName&tableName=java_word">選擇操作java_word表</a><br/>
        <a href="wordServlet?action=selectTableName&tableName=live_word">選擇操作live_word表</a><br/>
        <a href="wordServlet?action=selectTableName&tableName=temp_word">選擇操作temp_word表</a><br/>
        <a href="wordServlet?action=selectTableName&tableName=elementaryschool_word">選擇操作elementarySchool_word表</a><br/>
        <span id="tableNameSpan">當前操作表：${ empty sessionScope.tableName? "無" : sessionScope.tableName }</span><br/>

        <!-- 如果message有東西才顯示訊息標籤 -->
        <c:if test="${ !empty sessionScope.okMessage }">
            <span id="okMessage">${ sessionScope.okMessage }</span><br/>
        </c:if>
        <c:if test="${ !empty sessionScope.errorMessage }">
            <span id="errorMessage">${ sessionScope.errorMessage }</span><br/>
        </c:if>

        <%-- 顯示JavaWord 的Data 的table --%>
        <a href="index.jsp">回首頁</a>
        <a href="wordServlet?action=queryAll">顯示所有JavaWord</a>

        <%-- 添加word --%>
        <h2>添加word</h2>
        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="addWord">
            英文單字：<input type="text" name="word"><br/>
            中文意思：<input type="text" name="translation"><br/>
            備註：<input type="text" name="remarks"><br/>
            單字插入時間：<input type="text" name="createTime"><br/>
            <input type="submit" value="送出">
        </form>

        <%-- 刪除word 透過id --%>
        <h2>刪除word 透過id</h2>
        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="deleteWord">
            刪除的id：<input type="text" name="id"><br/>
            <input type="submit" value="送出">
        </form>

        <%-- 修改word透過id --%>
        <h2>修改word透過id</h2>
        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="updateWordById">
            要修改數據的id：<input type="text" name="id"><br/>
            新英文單字：<input type="text" name="word"><br/>
            新中文意思：<input type="text" name="translation"><br/>
            新備註：<input type="text" name="remarks"><br/>
            新單字插入時間：<input type="text" name="createTime"><br/>
            <input type="submit" value="送出">
        </form>

        <%-- 修改word透過English --%>
        <h2>修改word透過English</h2>
        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="updateWordByWord">
            要修改數據的英文單字：<input type="text" name="oldWord"><br/>
            新英文單字：<input type="text" name="word"><br/>
            新中文意思：<input type="text" name="translation"><br/>
            新備註：<input type="text" name="remarks"><br/>
            新單字插入時間：<input type="text" name="createTime"><br/>
            <input type="submit" value="送出">
        </form>

        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="queryWordById">
            要查詢的id：<input type="text" name="id"><br/>
            <input type="submit" value="送出">
        </form>

        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="queryWordByEnglish">
            要查詢的英文單字：<input type="text" name="word"><br/>
            <input type="submit" value="送出">
        </form>

        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="queryAll">
            <input type="submit" value="查詢全部數據">
        </form>

        <form action="wordServlet" method="post">
            <input type="hidden" name="action" value="queryCount">
            <input type="submit" value="查詢數據個數">
        </form>
    </div>


</body>
</html>
