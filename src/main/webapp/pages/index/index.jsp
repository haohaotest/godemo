<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>豪豪練英文</title>

    <!-- 靜態包含 base標籤動態獲取值 -->
    <%@ include file="../common/head.jsp" %>

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
        });
    </script>
</head>


<body>
<!-- 靜態包含 頂端導航欄 -->
<%@ include file="../common/title.jsp"%>

    <%-- 版心 --%>
    <div id="wrapper">

        <%-- 標題 --%>
        <h1>首頁</h1>
        <a href="pages/edit/data/crud.jsp">Edit Page</a><br/>
        <a href="wordServlet?action=queryAll">showAllData</a><br/>
        <a href="pages/edit/data/showQuery.jsp">showQuery</a><br/>

    </div>


</body>
</html>
