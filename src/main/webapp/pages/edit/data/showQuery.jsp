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

            background-color: #504f4f;
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

        #showQuery {
            margin-top: 15px;
        }

        /* 提示顯示區 - span */
        #promptMessage {
            display: inline-block;

            margin-top: 10px;

            width: 170px;
            height: 50px;

            background-color: rgba(80, 73, 73, 0.71);

            line-height: 50px;
        }

    </style>

    <script>
        $(function() {
            //答案文本框主動獲取焦點
            $("#answer").focus();

            //點提示鈕時 顯示答案的提示資訊
            $("#promptBtn").click(function() {
                $("#promptMessage").text("${ word.word }");
            });

            //沒填答案或填空白時 不給送出
            $("#submitByn").click(function() {
                let answer = $("#answer").val();
                if($.trim(answer) == "") {
                    return false;
                }
            });
        });
    </script>
</head>


<body>

<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../../common/title.jsp"%>

<%-- 版心 --%>
<div id="wrapper">

    <a href="pages/index/index.jsp">回首頁</a><br/>

    <form id="showQuery" action="queryOperate" method="post">
        <input type="hidden" name="action" value="showQuery">
        <input type="submit" value="出題">
    </form>

    <!-- 顯示題數 和題目 -->
    <div>
        <!-- 當前已經出題 且有總題數可顯示 才顯示 -->
        <c:if test="${ !empty requestScope.totalCount }">
            <h3>共${ requestScope.totalCount }題</h3>
        </c:if>

        <!-- 題目顯示 -->
        <h3>${ empty requestScope.word.translation?  "題目註釋" : requestScope.word.translation}</h3>
    </div>

    <!-- 輸入答案表單 -->
    <form action="queryOperate" method="post">
        <!-- 隱藏域 傳送資訊給後端 -->
        <input type="hidden" name="action" value="checkAnswer">
        <input type="hidden" name="query" value="${ requestScope.word.word }">

        <!-- 輸入答案的文本框 -->
        答案：<input id="answer" type="text" name="answer">
        <input id="submitByn" type="submit" value="送出">
    </form>

    <!-- 答錯答對的資訊顯示 -->
    <div>
        <span>${ requestScope.message }</span>
    </div>

    <!-- 提示按鈕 -->
    <button id="promptBtn">提示</button><br/>
    <!-- 提示資訊顯示 -->
    <span id="promptMessage"></span>
</div>


</body>
</html>
